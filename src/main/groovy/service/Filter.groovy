package service

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import model.User
import parser.Url

import java.util.stream.Collectors

class Filter {
    boolean getFilter(String urlString){
        Url url = new Url();
        String getUrl = url.getUrlContent(urlString)
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> users = null
        try {
            users = objectMapper.readValue(getUrl, new TypeReference<List<User>>() {})

        } catch (JsonParseException e) {
            println('ошибка при парсинге данных при помощи jkson')
            return false
        }
        List <User> sort = filterList(users)
        String result = ''
        for(User user : sort){
            result += user.id + ', ' + user.name + ', ' + user.email + ', ' + user.salary + '\n'
        }


        def data= [['id ', 'name ', 'email ', 'salary '],
                   [result]]
        def file = new File("./src/main/resources/users.csv")
        file.text = data*.join(',').join(System.lineSeparator())
        return true

    }


    List<User> filterList(List<User> users){
        List <User> sort = users.stream().filter (salary -> salary.salary > 3500)
                .filter (id -> id.id != '')
                .filter (name -> name.name != '')
                .filter (email -> email.email != '')
                .peek (email -> email.setEmail (email.email.replaceAll(',com', '.com').replaceAll(',ru', '.ru')))
                .sorted((o1, o2) -> o1.name <=> o2.name)
                .collect(Collectors.toList())
        return sort;
    }
}
