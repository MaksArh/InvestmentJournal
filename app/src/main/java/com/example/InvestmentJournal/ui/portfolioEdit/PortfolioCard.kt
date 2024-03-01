import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.InvestmentJournal.ui.auth.Portfolio

@Composable
fun PortfolioCard(portfolio: Portfolio, navController: NavHostController) {
    Card(modifier = Modifier.padding(8.dp).clickable {
        // Переход к деталям портфеля
        navController.navigate("portfolioDetails/${portfolio.id}")
    }) {
        Row(modifier = Modifier.padding(16.dp)) {
            Text(text = portfolio.name)
            Text(text = "${portfolio.startPrice}", modifier = Modifier.padding(start = 8.dp))
        }
    }
}
