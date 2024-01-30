package com.example.mvi_compose_basic.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mvi_compose_basic.data.MenuItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * 아이템 리스트를 생성하고 그 중 하나를 선택하면 드로어가 닫힌다.
 */
@Composable
fun DrawerBody(
    modifier: Modifier = Modifier,
    menuItems: List<MenuItem>,
    scaffoldState: ScaffoldState,
    scope: CoroutineScope,
    onItemClick: (MenuItem) -> Unit
) {
    //리스트뷰
    LazyColumn(modifier = modifier) {
        items(menuItems) { item ->
            DrawerItem(menuItem = item, modifier = modifier) {
                scope.launch {
                    scaffoldState.drawerState.close()
                }
                onItemClick(item)
            }
        }
    }
}