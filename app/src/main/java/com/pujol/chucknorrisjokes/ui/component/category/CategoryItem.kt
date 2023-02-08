package com.pujol.chucknorrisjokes.ui.component.category

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.pujol.chucknorrisjokes.R
import com.pujol.chucknorrisjokes.ui.theme.Background
import com.pujol.chucknorrisjokes.ui.theme.White

@Composable
fun CategoryItem(modifier: Modifier = Modifier, description: String, onClick: (String) -> Unit) {
    Card(modifier = modifier, shape = RoundedCornerShape(16.dp)) {
        ConstraintLayout(
            modifier = Modifier
                .clickable {
                    onClick(description)
                }
                .fillMaxWidth()
                .background(White)
        ) {
            val (title, icon) = createRefs()
            Text(
                text = description,
                modifier = Modifier
                    .padding(16.dp)
                    .constrainAs(title) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                fontSize = 24.sp
            )
            Icon(
                painterResource(id = R.drawable.ic_arrow_forward),
                contentDescription = null,
                tint = Background,
                modifier = Modifier
                    .size(24.dp)
                    .constrainAs(icon) {
                        end.linkTo(parent.end, margin = 16.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
            )
        }
    }
}