import service.Filter

class Main {


    static void main(String[] args) {
        Filter filter = new Filter()

        if (filter.getFilter('https://eltex-co.ru/test/users.php')){
            println('успешно сохранили cvs файл')
        } else {
            println('не удолось сохранить файл')
        }

    }

}
