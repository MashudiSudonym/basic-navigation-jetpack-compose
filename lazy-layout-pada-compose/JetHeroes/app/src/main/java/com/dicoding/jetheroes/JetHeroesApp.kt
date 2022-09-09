package com.dicoding.jetheroes

import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.compose.SubcomposeAsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.dicoding.jetheroes.ui.theme.JetHeroesTheme
import com.dicoding.jetheroes.data.HeroRepository
import com.dicoding.jetheroes.model.HeroesData
import kotlinx.coroutines.launch

@Composable
fun JetHeroesApp(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
//        val listState = rememberLazyListState()
//        val showButton: Boolean by remember { derivedStateOf { listState.firstVisibleItemIndex > 0 } }
//        val scope = rememberCoroutineScope()

        LazyColumn(
//            state = listState,
//            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            items(HeroesData.heroes, key = { it.id }) { hero ->
                HeroListItem(
                    name = hero.name,
                    photoUrl = hero.photoUrl,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
//        AnimatedVisibility(
//            visible = showButton,
//            enter = fadeIn() + slideInVertically(),
//            exit = fadeOut() + slideOutVertically(),
//            modifier = Modifier
//                .padding(bottom = 30.dp)
//                .align(Alignment.BottomCenter)
//        ) {
//            ScrollToTopButton(
//                onClick = {
//                    scope.launch {
//                        listState.scrollToItem(0)
//                    }
//                })
//        }
    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        placeholder = {
            Text(stringResource(R.string.search_hero))
        },
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .clip(RoundedCornerShape(16.dp))
    )
}

@Composable
fun HeroListItem(
    name: String,
    photoUrl: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable {}
    ) {
        AsyncImage(
            model = photoUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(60.dp)
                .clip(CircleShape)
        )
        Text(
            text = name,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 16.dp)
        )
    }
}

@Composable
fun CharacterHeader(
    char: String,
    modifier: Modifier = Modifier
) {
    Surface(
        color = colorResource(id = R.color.purple_500),
        modifier = modifier
    ) {
        Text(
            text = char,
            fontWeight = FontWeight.Black,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
    }
}

@Composable
fun ScrollToTopButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.shadow(10.dp, shape = CircleShape)
            .clip(shape = CircleShape)
            .size(56.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
            contentColor = colorResource(id = R.color.purple_500)
        )
    ) {
        Icon(
            imageVector = Icons.Filled.KeyboardArrowUp,
            contentDescription = null,
        )
    }

}

@Preview(showBackground = true)
@Composable
fun JetHeroesAppPreview() {
    JetHeroesTheme {
        JetHeroesApp()
    }
}

@Preview(showBackground = true)
@Composable
fun HeroListItemPreview() {
    JetHeroesTheme {
        val hero = HeroesData.heroes[0]
        HeroListItem(
            name = "H.O.S. Cokroaminoto",
            photoUrl = ""
        )
    }
}