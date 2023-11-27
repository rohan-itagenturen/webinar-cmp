package ui.screens.apidemo

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import domain.models.User
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import org.jetbrains.compose.resources.painterResource
import ui.utils.ViewState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApiDemoScreen(viewModel: ApiDemoViewModel, onBackPressed:()->Unit) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Users") },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Go Back"
                        )
                    }
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.TopCenter
        ) {
            when (uiState.value.state) {
                ViewState.LOADING -> LoadingView()
                ViewState.LOADED -> UsersView(uiState.value.users)
                ViewState.ERROR -> Text("Unable to Load Error")
            }
        }
    }

}

@Composable
fun LoadingView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun UsersView(users: List<User>) {
    LazyColumn {
        items(users.size) { index ->
            val user = users[index]
            ListItem(
                modifier = Modifier.clickable { },
                headlineContent = { Text(text = user.fullName) },
                supportingContent = { Text(text = user.email) },
                leadingContent = {
                    KamelImage(
                        modifier = Modifier.size(70.dp).clip(RoundedCornerShape(10)),
                        resource = asyncPainterResource(user.avatar),
                        contentDescription = user.fullName,
                        contentScale = ContentScale.Crop
                    )
                }
            )
        }
    }
}