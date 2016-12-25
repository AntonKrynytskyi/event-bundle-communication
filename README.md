# Event Bundle Communication

## Simple Example

### Description:
* */mrgr3n/ebc/info* - mapped to servlet, which trigger event to creating orders with some products;
* *items* - number products, which will be created under current order.	

### Go to the next link:
> http://[host]:[port]/mrgr3n/ebc/info?items=4  
> for example: http://localhost:8080/mrgr3n/ebc/info?items=4

on screen you will be see id of future orders:
	'Id: 9e054a4f-4a7b-419b-8372-05a59c70b388'

*Note:* log will be printed at the console(step by step, you could see how it work).

## Чтобы послать событие, необходимо:
1. Получить экземпляр сервиса EventAdmin из OSGi.
2. Выбрать топик события, удовлетворяющий доменной модели.
3. Заполнить словарь (класс Dictionary) свойств события.
4. Вызвать метод sendEvent или postEvent полученного экземпляра сервиса.

* источник события – EventAdmin
* обработчик события – EventHandler
* непосредственно само событие – Event
* postEvent - отправить событие для асинхронной обработки 
* sendEvent - отправить событие для синхронной обработки

### Стоит обратить внимание, что диспетчер событий в OSGi не сохраняет события, т.е., если бандл-обработчик еще не запущен - событие будет потеряно. Это необходимо помнить при организации правильного порядка запуска бандлов.
