import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.InvestmentJournal.models.SharedPreferencesManager
import com.example.InvestmentJournal.ui.auth.ApiService
import com.example.InvestmentJournal.ui.auth.Portfolio
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PortfolioMonitorningPage(viewModel: PortfolioMonitorningViewModel, navHostController: NavHostController) {
    var showDialog by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Редактирование портфелей", style = MaterialTheme.typography.h5)
        Divider(modifier = Modifier.padding(vertical = 8.dp))

        viewModel.portfolios.forEach { portfolio ->
            PortfolioCard(portfolio, navHostController)
        }

        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Создать портфель")
            }
        }
    }
}





class PortfolioMonitorningViewModel(private val apiService: ApiService) : ViewModel() {

    val portfolios = mutableStateListOf<Portfolio>()

    init {
        loadPortfolios()
    }

    private fun loadPortfolios() {
        viewModelScope.launch {
            try {
                // Прямой вызов suspend функции
                val portfoliosList = apiService.getUserPortfolios(SharedPreferencesManager.getUser()) // Замените на ваш метод и параметры
                // Обновите список портфелей в UI
                portfolios.clear()
                portfolios.addAll(portfoliosList)
            } catch (e: Exception) {
                // Обработка ошибки запроса
                e.printStackTrace()
            }
            delay(1000) // Перезагрузка каждую секунду
        }
    }

    // Пример создания нового портфеля
    fun createPortfolio(portfolioName: String) {
        viewModelScope.launch {
            try {
                val portfolio = Portfolio(id = 0, name = portfolioName, startPrice = 0.0f, currPrice = 0.0f)
                apiService.createPortfolio(SharedPreferencesManager.getUser(), portfolio) // Используйте ваш метод
                loadPortfolios() // Перезагружаем список после добавления
            } catch (e: Exception) {
                // Обработка ошибки
                e.printStackTrace()
            }
        }
    }


}
