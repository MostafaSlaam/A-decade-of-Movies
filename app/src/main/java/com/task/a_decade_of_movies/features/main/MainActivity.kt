package com.task.a_decade_of_movies.features.main

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarHost
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.task.a_decade_of_movies.core.AppPreferences
import com.task.a_decade_of_movies.route.NavigationGraph
import com.task.a_decade_of_movies.ui.theme.AdecadeofMoviesTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.Locale

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.setBackgroundDrawable(null)
        setContent {
            val scope = rememberCoroutineScope()
            AdecadeofMoviesTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                val coroutineScope = rememberCoroutineScope()

                    Scaffold(
                        scaffoldState = scaffoldState,
                        modifier = Modifier.navigationBarsPadding(),
                    ) {
                        Body(
                            modifier = Modifier.padding(it),
                            it,
                            navController = navController,
                            viewModel,
                            coroutineScope,
                            scaffoldState,
                        )


                    }

            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { view, insets ->
            val bottom = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
            view.updatePadding(bottom = bottom)
            insets
        }
    }

    override fun attachBaseContext(newBase: Context) {
        val appLang = AppPreferences.getLocale()
        val newOverride = Configuration(newBase.resources.configuration)
        val context = setAppLocal(newBase, appLang)
        super.attachBaseContext(context)
    }

    private fun setAppLocal(mContext: Context, localCode: String): Context {
        var context = mContext
        val res = context.resources
        val configuration = res.configuration
        configuration.setLocale(Locale(localCode.lowercase(Locale.getDefault())))
        context = mContext.createConfigurationContext(configuration)
        return context
    }
}

@ExperimentalMaterialApi
@Composable
fun Body(
    modifier: Modifier,
    paddingValues: PaddingValues,
    navController: NavHostController,
    mainViewModel: MainViewModel,
    coroutineScope: CoroutineScope,
    scaffoldState: ScaffoldState
) {
    Column {
        NavigationGraph(
            navController,
            paddingValues,
            mainViewModel,
            coroutineScope,
            scaffoldState
        )
    }

}