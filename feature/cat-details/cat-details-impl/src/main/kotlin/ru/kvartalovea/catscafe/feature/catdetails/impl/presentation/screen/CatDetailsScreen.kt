package ru.kvartalovea.catscafe.feature.catdetails.impl.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import ru.kvartalovea.catscafe.feature.catdetails.impl.R
import ru.kvartalovea.catscafe.feature.catdetails.impl.presentation.model.CatDetailUiModel
import ru.kvartalovea.catscafe.feature.catdetails.impl.presentation.model.CatDetailsUiEvent
import ru.kvartalovea.catscafe.feature.catdetails.impl.presentation.model.CatDetailsUiState
import ru.kvartalovea.catscafe.feature.catdetails.impl.presentation.viewmodel.CatDetailsViewModel

private val HERO_IMAGE_HEIGHT = 280.dp
private val CARD_OVERLAP = 24.dp

@Composable
internal fun CatDetailsScreen(
    catId: String,
    viewModel: CatDetailsViewModel = koinViewModel(parameters = { parametersOf(catId) }),
) {
    val state by viewModel.state.collectAsState()
    CatDetailsContent(state = state, onEvent = viewModel::onEvent)
}

@Composable
private fun CatDetailsContent(
    state: CatDetailsUiState,
    onEvent: (CatDetailsUiEvent) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (state) {
            is CatDetailsUiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is CatDetailsUiState.NotFound -> {
                Text(
                    text = stringResource(R.string.cat_details_not_found),
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

            is CatDetailsUiState.Content -> {
                CatDetailsBody(cat = state.cat, onEvent = onEvent)
            }
        }

        // Floating back button — visible in all states
        BackButton(
            modifier = Modifier
                .align(Alignment.TopStart)
                .statusBarsPadding()
                .padding(12.dp),
            onClick = { onEvent(CatDetailsUiEvent.OnBackClick) },
        )
    }
}

@Composable
private fun CatDetailsBody(
    cat: CatDetailUiModel,
    onEvent: (CatDetailsUiEvent) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Scrollable content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            // Hero image
            AsyncImage(
                model = cat.imageUrl,
                contentDescription = cat.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(HERO_IMAGE_HEIGHT),
                contentScale = ContentScale.Crop,
            )

            // White card overlapping image bottom
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = -CARD_OVERLAP),
                shape = RoundedCornerShape(topStart = CARD_OVERLAP, topEnd = CARD_OVERLAP),
                color = MaterialTheme.colorScheme.background,
            ) {
                Column(
                    modifier = Modifier.padding(
                        top = 20.dp,
                        start = 20.dp,
                        end = 20.dp,
                        // Extra bottom padding so last content is not covered by fixed buttons
                        bottom = 104.dp,
                    ),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                ) {
                    // Name + status badge
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            text = cat.name,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                        )
                        if (cat.isLookingForHome) {
                            LookingForHomeBadge()
                        }
                    }

                    // Characteristics card
                    CharacteristicsCard(cat = cat)

                    // Bio section
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(
                            text = stringResource(R.string.cat_details_about, cat.name),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = cat.bio,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = MaterialTheme.typography.bodyMedium.lineHeight,
                        )
                    }
                }
            }
        }

        // Fixed bottom action buttons
        ActionButtons(
            modifier = Modifier.align(Alignment.BottomCenter),
            onHelpClick = { onEvent(CatDetailsUiEvent.OnHelpCatClick) },
            onBookClick = { onEvent(CatDetailsUiEvent.OnBookVisitClick) },
        )
    }
}

@Composable
private fun BackButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(Color.White.copy(alpha = 0.92f))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
            contentDescription = stringResource(R.string.cat_details_back),
            tint = Color.Black,
            modifier = Modifier.size(20.dp),
        )
    }
}

@Composable
private fun LookingForHomeBadge() {
    Surface(
        shape = RoundedCornerShape(50),
        color = Color(0xFFE8F5E9),
    ) {
        Text(
            text = stringResource(R.string.cat_details_looking_for_home),
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelMedium,
            color = Color(0xFF2E7D32),
            fontWeight = FontWeight.Medium,
        )
    }
}

@Composable
private fun CharacteristicsCard(cat: CatDetailUiModel) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
            Text(
                text = stringResource(R.string.cat_details_characteristics),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp),
            )
            CharacteristicRow(label = stringResource(R.string.cat_details_age), value = cat.age)
            Spacer(modifier = Modifier.height(8.dp))
            CharacteristicRow(label = stringResource(R.string.cat_details_breed), value = cat.breed)
            Spacer(modifier = Modifier.height(8.dp))
            CharacteristicRow(label = stringResource(R.string.cat_details_temperament), value = cat.temperament)
        }
    }
}

@Composable
private fun CharacteristicRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
        )
    }
}

@Composable
private fun ActionButtons(
    modifier: Modifier = Modifier,
    onHelpClick: () -> Unit,
    onBookClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Button(
            onClick = onHelpClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
            ),
        ) {
            Icon(
                imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription = null,
                modifier = Modifier.size(18.dp),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = stringResource(R.string.cat_details_help_cat), fontWeight = FontWeight.Medium)
        }
        Button(
            onClick = onBookClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1C1C1E),
                contentColor = Color.White,
            ),
        ) {
            Text(text = stringResource(R.string.cat_details_book_visit), fontWeight = FontWeight.Medium)
        }
    }
}
