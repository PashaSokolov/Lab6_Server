import java.io.FileNotFoundException;
import java.util.logging.Logger;

public class Terminal {
    static Logger logger = Logger.getLogger("TerminalLogger");
    private Server server;
    private MovieList movieList;
    private Clear clear = new Clear();
    private String newLine = new String();
    private Info info = new Info();
    private Show show = new Show();
    private Add add = new Add();
    private Update update = new Update();
    private RemoveById removeById = new RemoveById();
    private Save save = new Save();
    private RemoveFirst removeFirst = new RemoveFirst();
    private AddIfMax addIfMax = new AddIfMax();
    private ExecuteScript executeScript;
    private FilterContains filterContains = new FilterContains();
    private PrintAscending printAscending = new PrintAscending();
    private PrintDiscending printDiscending = new PrintDiscending();
    private RemoveIndex remove_index = new RemoveIndex();
    private Respond respond = new Respond();

    public Terminal(MovieList movieList, Server server) {
        this.movieList = movieList;
        this.server = server;
        executeScript = new ExecuteScript(this, server);
        logger.finest("Terminal created successfully");
    }

    public void start() {
        while (true) {
            startWithCommand(server.getCommand());
        }
    }

    public void startWithCommand(Command command) {
        logger.info("Server is in process of executing the command \"" + command.getCommandname() + "\"");
        switch (command.getCommandname()) {
            case "server_exit":
                logger.info("Server is closed now");
                System.exit(0);
                break;
            case "clear":
                clear.execute(movieList);
                respond.setMsg(clear.getMessage());
                server.writeRespond(respond);
                break;
            case "help":
                respond.setMsg(
                        "help : вывести справку по доступным командам\n" +
                                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                                "add {element} : добавить новый элемент в коллекцию\n" +
                                "update {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                                "remove_by_id id : удалить элемент из коллекции по его id\n" +
                                "clear : очистить коллекцию\n" +
                                "save : сохранить коллекцию в файл\n" +
                                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                                "exit : завершить программу (без сохранения в файл)\n" +
                                "remove_at index : удалить элемент, находящийся в заданной позиции коллекции (index)\n" +
                                "remove_first : удалить первый элемент из коллекции\n" +
                                "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" +
                                "filter_contains_name name : вывести элементы, значение поля name которых содержит заданную подстроку\n" +
                                "print_ascending : вывести значения поля mpaaRating всех элементов в порядке возрастания\n" +
                                "print_discending : вывести значения поля mpaaRating всех элементов в порядке убывания"
                );
                server.writeRespond(respond);
                break;
            case "info":
                info.execute(movieList);
                respond.setMsg(info.getMessage());
                server.writeRespond(respond);
                break;
            case "remove_at":
                if (command.getArgument() == null) {
                    remove_index.execute(movieList, null);
                } else {
                    remove_index.execute(movieList, Integer.valueOf((String) command.getArgument()));
                }
                respond.setMsg(remove_index.getMessage());
                server.writeRespond(respond);
                break;

            case "show":
                show.execute(movieList);
                respond.setMsg(show.getMessage());
                server.writeRespond(respond);
                break;
            case "print_ascending":
                printAscending.execute(movieList);
                respond.setMsg(printAscending.getMessage());
                server.writeRespond(respond);
                break;
            case "print_discending":
                printDiscending.execute(movieList);
                respond.setMsg(printDiscending.getMessage());
                server.writeRespond(respond);
                break;
            case "add":
                add.execute(movieList, (Movie) command.getArgument());
                respond.setMsg(add.getMessage());
                server.writeRespond(respond);
                break;
            case "save":
                save.execute(movieList);
                respond.setMsg(save.getMessage());
                server.writeRespond(respond);
                break;
            case "add_if_max":
                addIfMax.execute(movieList, (Movie) command.getArgument());
                respond.setMsg(addIfMax.getMessage());
                server.writeRespond(respond);
                break;
            case "remove_first":
                removeFirst.execute(movieList);
                respond.setMsg(removeFirst.getMessage());
                server.writeRespond(respond);
                break;
            case "filter_contains_name":
                if (command.getArgument() != null) {
                    filterContains.execute(movieList, (String) command.getArgument());
                    respond.setMsg(filterContains.getMessage());
                } else {
                    respond.setMsg("Filter is null");
                }
                server.writeRespond(respond);
                break;
            case "remove_by_id":
                if (command.getArgument() == null) {
                    removeById.execute(movieList, (Long) null);
                } else {
                    removeById.execute(movieList, Long.valueOf((String) command.getArgument()));
                }
                respond.setMsg(removeById.getMessage());
                server.writeRespond(respond);
                break;

            case "execute_script":
                LoopAnalyzer la = new LoopAnalyzer();
                try {
                    if (la.isLooped((String) command.getArgument())) {
                        respond.setMsg("EoI");
                        server.writeRespond(respond);
                        logger.warning("Loop was found");
                        respond.setMsg("Loop found. Remove it or try another script");
                        server.writeRespond(respond);
                    } else {
                        executeScript.execute(movieList, (String) command.getArgument());
                        respond.setMsg("EoI");
                        server.writeRespond(respond);
                        respond.setMsg(executeScript.getMessage());
                        server.writeRespond(respond);
                    }
                } catch (FileNotFoundException e) {
                    respond.setMsg("EoI");
                    server.writeRespond(respond);
                    logger.warning("File not found");
                    respond.setMsg("File not found");
                    server.writeRespond(respond);
                } catch (SecurityException e) {
                    respond.setMsg("EoI");
                    server.writeRespond(respond);
                    logger.warning("File unacceptable");
                    respond.setMsg("File unacceptable");
                    server.writeRespond(respond);
                }
                break;
            case "update":
                if (command.getArgument() != null && command.getAdditional() != null) {
                    update.execute(movieList, (Long) command.getAdditional(), (Movie) command.getArgument());
                    respond.setMsg(update.getMessage());
                    server.writeRespond(respond);
                } else {
                    update.execute(movieList, null);
                    respond.setMsg(update.getMessage());
                    server.writeRespond(respond);
                }
                break;
            default:
                logger.info("Unknown command");
                respond.setMsg("Unknown command");
                server.writeRespond(respond);
                break;

        }
        logger.info("Command executed successfully");
    }
}