package com.revature.popquiz.view.screens

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.example.androiddevchallenge.presentation.searchbarcomponents.searchbar.quizBarSearch
import com.revature.popquiz.MainActivity
import com.revature.popquiz.view.shared.QuizScaffold
import com.revature.popquiz.viewmodels.SearchBarViewModel
import com.revature.popquiz.view.shared.QuizCardForLazyColumn as QuizCardForLazyColumn

@ExperimentalAnimationApi
@Composable
fun SearchQuizzesScreen(navController: NavController)
{
    //navController: NavController
    val scaffoldState = rememberScaffoldState()
    val context= LocalContext.current

    QuizScaffold(
        sTitle = "Search Quizzes",
        navController = navController
    )
    {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            SearchQuizzesBody()
        }

    }
}


@ExperimentalAnimationApi
@Composable
fun SearchQuizzesBody()
{
    var context = LocalContext.current
    var searchBarViewModel = ViewModelProvider(context as MainActivity).get(SearchBarViewModel::class.java)
    var x = searchBarViewModel.sSearchValue
    var sSearchValue by remember { mutableStateOf(x) }
//    sSearchValue = searchBarViewModel.sSearchValue
    val lazyState = rememberLazyListState()

    searchBarViewModel.sortBySearch() //sorts the list

    Card(
        modifier = Modifier
            .fillMaxSize()
            .absolutePadding(
                top = 5.dp,
            ),
        shape = AbsoluteRoundedCornerShape(
            topLeft = 20.dp,
            topRight = 20.dp
        ),
        elevation = 10.dp
    )
    {
        // Search bar goes here

        LazyColumn(

            state = lazyState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 50.dp)
                .background(
                    color = Color.Transparent
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        )
        {
            item()
            {
                quizBarSearch()
                Log.d("Search bar", "$sSearchValue")
            }

            item { 
                Text(text = "Wow here's the value: ${searchBarViewModel.sSearchValue}")

            }
            item{
                QuizCardForLazyColumn(
                    quizTitleText = "Java Basics",
                    shortQuizDescriptionText = "Short quiz description"
                )
            }

            item{
                QuizCardForLazyColumn(
                    quizTitleText = "Kotlin Fundamentals",
                    shortQuizDescriptionText = "Short quiz description"
                )
            }

            item{
                QuizCardForLazyColumn(
                    quizTitleText = "Intro to Databases",
                    shortQuizDescriptionText = "Short quiz description"
                )
            }

            item{
                QuizCardForLazyColumn(
                    quizTitleText = "MVVM Design Pattern",
                    shortQuizDescriptionText = "Short quiz description"
                )
            }


            item{
                QuizCardForLazyColumn(
                    quizTitleText = "Quiz Title",
                    shortQuizDescriptionText = "Short quiz description"
                )
            }


            item{
                QuizCardForLazyColumn(
                    quizTitleText = "Quiz Title",
                    shortQuizDescriptionText = "Short quiz description"
                )
            }
        }
    }
}


@Composable
fun quizTags()
{
    Text(
        text = "Tags: ",
        textAlign = TextAlign.Center
    )
    // Insert tags here

    val tags: Set<String>
    tags = setOf("Language", " Topic", " Quiz type")

    Text(
        text = tags.toString(),
        textAlign = TextAlign.Center
    )
}