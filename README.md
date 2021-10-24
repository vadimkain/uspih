# uspih
### Регистрация
Когда пользователь нажмёт кнопку "зарегистрироваться", прежде чем данные отправятся в бэк - html проверит условие правильности ввода электронной почты и ввода капчи. Если всё в порядке - данные отправляются в бэк. Сначала принимает данные контролер, который в свою очередь проверяет совпадают ли пароль и пароль подтверждения, если нет - возвращает ошибку. За тем отправляем в один из булевых методов UserService, который проверяет существования такого пользователя. Взаимосвязь происходит с UserRepo (интерфейс который работает с БД). Если пользователь есть - возвращает false и в контроллере если в условие поступает false - уведомляем пользователя о том, что такой пользователь уже есть. Иначе, если такого пользователя нет - сохраняем его и генерируем в activation_code код активации. Вместе с этим генерируются базовые данные: главный счёт и категория зарплата, которые нельзя удалить в логических целях.

### Добавление данных
Заходя на главную страницу подгружается MainController, который возвращает пользователю скрытые некоторые логические данные, необходимые для корректного отображения данных.
При нажатии и каком-либо действии отрабатывается MainController, который выполняет логические операции, что позволяет пользователю дальше работать со страничкой.
### ValidationController
Во первых во избежании индекс ерроров идёт проверка на то, что в полученной строке >= 3 символов, если условие верно -
проверяем условие: 1) если первый символ строки == минус и второй символ строки равен нулю, и третий символ строки != точке, значит это неверный ввод, т.к. не может быть больше двух
нулей в начале, обязательно должна после нуля идти точка. Иначе - пробуем исключение parseDouble (число полностью введено верно - true, иначе false). 2) иначе если длина строки >= 2 
символа, то если первый символ строки == нулю и второй символ строки != точке, значит возвращаем false, иначе пробуем бросить исключение parseDouble 3) иначе просто бросаем исключение
parseDouble и проверяем что вернёт - исключение или выполнится. 
#### Добавление нового счёта
После нажатия на кнопку добавления счёта открывается форма - это следствие работы MainController. Пользователь заполняет форму. Когда нажимает отправить - прежде, чем на сервер
прийдёт POST запрос, JS провалидирует прямо на ходу вводимые данные пользователя на то, чтобы в денежном поле были только цифры, минусы и точки. Если все условия удовлетворены - 
отправляется POST запрос на BankController, где также проходят некоторые второстепенные скрытые логические операции для корреткного отображения данных после чего начинается 
проверка, чтобы ничего не было пустое. Заключительная проверка - правильность ввода денежного значения, оно выполняется на стороне сервера в ValidationService. Это очень важный
алгоритм, от которого зависит вся работа денежной системы (смотреть выше данный алгоритм). Если валидация успешна - обращаемся в BankService в модуль NewBank и передаём туда
значения, полученные от пользователя - user, title_bank, doubleScore. NewBank принимает данные, приводит их в вид и отправляет в bankRepo через модуль save сохраняя их в БД.
Тем самым когда это всё выполнилось успешно - пользователя редиректит на главную страницу и ему выволится обновлённый список текущих счетов.
### Удаление счёта
После нажатия на кнопку удаление счёта открывается форма - это следствие работы MainController. Пользователь выбирает чекбоксы, но не может выбрать главный счёт. На сервер
отправляется AuthenticationPrincipal User user и словарь ключ-значение формы Map<>. Ничего не валидируем, так как нет необходимости. Отправляем данные в BankService.DelteBank(User owner, Map<String, String> form) где сначала получаем список счетов по запросу в БД по признакам владельца, а за тем делаем обход по спискам через цикл for (BIG-O: N+N^3). Во время обхода по спискам происходит ещё один важный алгоритм - АРХИВИРОВАНИЕ ТРАНЗАКЦИЙ, ЕСЛИ ЕСТЬ ТРАНЗАКЦИЯ С ТАКИМ БАНКОМ ПО ПРИЗНАКУ ВЛАДЕЛЬЦА и только после этого удаляем банк. При успешном выполнении редиректим на главную страницу.
