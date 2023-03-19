# UITest

## Установка 

1. Установите сборщик maven
2. Скачайте chromedriver и поместите в папку resources
3. Выполните `mvn install` в корне проекта
4. Запустите тесты (файл TestCases.java)
5. Открыть веб-интерфейс c отчётом allure можно командой `mvn allure:serve`

Примечание: есть возможность открыть веб-интерфейс allure без установки, запустив файл target/site/index.html.

## Тест-кейсы

### Кейс 1. Проверка успешной покупки
#### Предусловие:
1. Открыть браузер
2. Перейти на страницу входа: https://www.saucedemo.com/
3. Заполнить поле Username значением standard_user
4. Заполнить поле Password значением secret_sauce
5. Нажать на кнопку LOGIN

#### Шаги:
1. Положить первый продукт в списке на странице PRODUCTS в корзину кнопкой ADD TO CART
2. Нажать на иконку корзины.
3. На открывшейся странице корзины нажать кнопку CHECKOUT
5. На открывшейся странице CHECKOUT: YOUR INFORMATION заполнить поля First Name, Last
Name, Zip Code значением test
6. Нажать на кнопку CONTINUE
7. На открывшейся странице CHECKOUT: OVERVIEW нажать кнопку FINISH

#### Ожидаемый результат:
1. Произошел редирект на страницу завершения покупки:
https://www.saucedemo.com/checkout-complete.html
2. На странице отображается сообщение: “THANK YOU FOR YOUR ORDER”

### Кейс 2. Проверка сообщения об ошибке при попытке ввода логина на несуществующего пользователя

#### Предусловие:
1. Открыть браузер
2. Перейти на страницу входа: https://www.saucedemo.com/

#### Шаги:
1. Заполнить поле Username значением test
2. Заполнить поле Password значением test
3. Нажать на кнопку LOGIN
#### Ожидаемый результат:
1. На странице появилось сообщение: “Epic sadface: Username and password do not match any
user in this service”

## Результаты тестирования

![allure1](https://user-images.githubusercontent.com/124812541/226161853-20755375-deae-42cf-9af2-da2c4d35de02.png)

![allure2](https://user-images.githubusercontent.com/124812541/226161858-dd65faab-e2f1-4232-afb6-c73cc873b6cd.png)

