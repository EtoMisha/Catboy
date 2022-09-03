Простое приложение, чтоб освоиться в работе со Spring Boot и MVC.

Обращается по API к https://catboys.com/api, получает json и парсит из него ответ.

Понимает два GET запроса: ping и catboy.

- Запросы отправляются через RestTemplate

- Парсинг json с помощью JSONObject

- Логгирование - slf4j