package com.dipen.newsapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dipen.newsapp.R
import com.dipen.newsapp.data.entity.Article
import com.dipen.newsapp.ui.theme.Purple40

@Composable
fun Loader() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(60.dp)
                .padding(10.dp),
            color = Purple40
        )
    }

}


@Composable
fun NewsRowComponent(page: Int, article: Article) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(Color.White),
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp),
            model = article.urlToImage, contentDescription = "",
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.placeholder),
            error = painterResource(id = R.drawable.placeholder)
        )

        Spacer(modifier = Modifier.size(20.dp))

        HeadingText(textValue = article.title ?: "")

        Spacer(modifier = Modifier.size(10.dp))

        NormalText(textValue = article.content ?: article.description ?: "")

        Spacer(modifier = Modifier.weight(1f))

        AuthorDetailsComponent(article.author, article.source?.name)

    }
}

@Preview
@Composable
fun NewsRowComponentPreview() {
    NewsRowComponent(
        page = 0,
        article = Article(
            author = "XYZ",
            title = "Aaj ki taaza khabar",
            content = "Description",
            description = "Description",
            publishedAt = "",
            source = null,
            url = null,
            urlToImage = ""
        )
    )
}

@Composable
fun NormalText(textValue: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .wrapContentHeight(),
        text = textValue,
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.Monospace,
            color = Purple40
        )
    )
}

@Composable
fun HeadingText(textValue: String, isInCenter: Boolean = false) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .wrapContentHeight(),
        text = textValue,
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        ),
        textAlign = if (isInCenter) TextAlign.Center else TextAlign.Start
    )
}

@Composable
fun AuthorDetailsComponent(authorName: String?, sourceName: String?) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp, top = 10.dp, start = 10.dp, end = 10.dp)
    ) {
        authorName?.let { Text(text = it, style = TextStyle(color = Color.Black)) }
        Spacer(modifier = Modifier.weight(1f))
        sourceName?.let { Text(text = it, style = TextStyle(color = Color.Black)) }
    }
}

@Composable
fun EmptyState() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_empty_state),
            contentDescription = "Empty"
        )
        HeadingText(textValue = stringResource(id = R.string.error_fetching_news), true)
    }
}