package com.pujol.chucknorrisjokes.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.pujol.chucknorrisjokes.R
import com.pujol.chucknorrisjokes.ui.theme.ToolbarBackGround

@Composable
fun Toolbar(description: String, enabledBack: Boolean = true, onBackPressed: () -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = ToolbarBackGround)
            .height(80.dp)
    ) {
        val (icon, title) = createRefs()

        if (enabledBack) {
            Icon(
                painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = null,
                modifier = Modifier
                    .clickable {
                        onBackPressed()
                    }
                    .size(24.dp)
                    .constrainAs(icon) {
                        start.linkTo(parent.start, margin = 16.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
            )
        }
        Text(
            text = description,
            modifier = Modifier
                .padding(16.dp)
                .constrainAs(title) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                },
            fontSize = 24.sp
        )

    }
}