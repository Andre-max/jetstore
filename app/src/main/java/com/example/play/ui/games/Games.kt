package com.example.play.ui.games

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.play.data.AppRepo
import com.example.play.theme.PlayTheme
import com.example.play.ui.apps.applist.ForYouLayout
import com.example.play.ui.apps.applist.TopChartsLayout
import com.example.play.ui.components.PlaySurface
import com.example.play.ui.main.AppsCategory
import com.example.play.ui.main.AppsCategory.Categories
import com.example.play.ui.main.AppsCategory.EarlyAccess
import com.example.play.ui.main.AppsCategory.EditorsChoice
import com.example.play.ui.main.AppsCategory.ForYou
import com.example.play.ui.main.AppsCategory.TopCharts
import com.example.play.ui.main.AppsCategoryTabs
import com.google.accompanist.insets.navigationBarsPadding

@Composable
fun Games(
  onAppClick: (Long) -> Unit,
  modifier: Modifier = Modifier,
  appsCategories: List<AppsCategory> = listOf(
      ForYou, TopCharts, Categories, EditorsChoice, EarlyAccess
  )
) {
  val forYouData = remember { AppRepo.getForYouGames() }
  val topChartsData = remember { AppRepo.getTopChartsGames() }
  val (currentCategory, setCurrentCategory) = rememberSaveable { mutableStateOf(ForYou) }

  PlaySurface(modifier = modifier.fillMaxSize()) {
    Column(modifier = Modifier.navigationBarsPadding(left = true, right = true)) {
      AppsCategoryTabs(
          categories = appsCategories,
          selectedCategory = currentCategory,
          onCategorySelected = setCurrentCategory
      )
      val tweenSpec = remember {
        TweenSpec<Float>(
            durationMillis = 600,
            easing = LinearOutSlowInEasing
        )
      }
      Crossfade(currentCategory, animationSpec = tweenSpec) { category ->
        when (category) {
          ForYou -> ForYouLayout(forYouData, onAppClick)
          TopCharts -> TopChartsLayout(topChartsData, onAppClick)
          else -> ForYouLayout(forYouData, onAppClick)
        }
      }
    }
  }
}

@Preview("Games")
@Composable
fun GamesPreview() {
  PlayTheme {
    Games(onAppClick = {})
  }
}

@Preview("Games • Dark Theme")
@Composable
fun GamesDarkPreview() {
  PlayTheme(darkTheme = true) {
    Games(onAppClick = {})
  }
}