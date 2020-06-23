import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

public class NullPointerChecker implements Checker {
    static Logger logger = Logger.getLogger("NullCheckLogger");
    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private String newLine = new String();

    @Override
    public void checkEverything(Movie movie, CheckParametr parametr) {
        if (parametr == CheckParametr.WITH_ASKING) {
            if (movie.getName() == null) {
                boolean passedValue = false;
                System.out.println("Looks like the movie name is null. \n Enter the new name:");

                while (!passedValue) {
                    try {
                        if ((newLine = in.readLine()) != "") {
                            movie.setName(newLine);
                            passedValue = true;
                        } else {
                            System.out.println("Entered value is inappropriate. Try another one: ");
                        }
                    } catch (NoSuchElementException e) {
                        System.out.println("End of input. Field will be replaced with default value");
                        movie.setName("Movie" + movie.getId());
                        passedValue = true;
                    } catch (Exception e) {
                        System.out.println("Пожалуйста примите исполнение");
                        System.exit(0);
                    }
                }
            }

            if (movie.getCoordinates() == null) {
                System.out.println("Looks like movie coordinates are null. \n Enter the new coordinates:");

                movie.setCoordinates(new Coordinates(0, 0));
                changeX(movie);
                changeY(movie);
            }

            if (Long.valueOf(movie.getCoordinates().getX()) == null) {
                System.out.println("Looks like the movie X-coordinate is null");
                changeX(movie);
            }

            if (Long.valueOf((long) movie.getCoordinates().getY()) == null) {
                System.out.println("Looks like the movie X-coordinate is null");
                changeY(movie);
            }
            if (Long.valueOf(movie.getOscarcount()) == null) {
                boolean passedValue = false;
                System.out.println("Looks like the movie oscarCount is null. \n Enter the new oscarCount");

                while (!passedValue) {
                    try {

                        Long tempOscar = Long.parseLong(newLine = in.readLine());

                        if (tempOscar > 0) {
                            movie.setOscarsCount(tempOscar);
                            passedValue = true;
                        } else {
                            System.out.println("Entered value is inappropriate. Try another one");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("This is not a number");
                    } catch (NoSuchElementException e) {
                        System.out.println("Field will be replaced with random value");
                        movie.setOscarsCount((long) (Math.random() * 10));
                        passedValue = true;
                    } catch (Exception e) {
                        System.out.println("Пожалуйста примите исполнение");
                        System.exit(0);
                    }
                }

            }
            if (movie.getDirector().getName() == null) {
                boolean passedValue = false;
                System.out.println("Looks like the director name is null. \n Enter the new name:");

                while (!passedValue) {
                    try {
                        if ((newLine = in.readLine()) != "") {
                            movie.setName(newLine);
                            passedValue = true;
                        } else {
                            System.out.println("Entered value is inappropriate. Try another one: ");
                        }
                    } catch (NoSuchElementException e) {
                        System.out.println("End of input. Field will be replaced with default value");
                        movie.getDirector().setName("Director" + movie.getId());
                        passedValue = true;
                    } catch (Exception e) {
                        System.out.println("Пожалуйста примите исполнение");
                        System.exit(0);
                    }
                }

            }
            if (movie.getDirector().getCountry() == null) {
                while (true) {
                    System.out.println("\n" + "Looks like director country is null");
                    System.out.println("Try this:" + "\n"
                            + "UNITED_KINGDOM" + "\n"
                            + "ITALY" + "\n"
                            + "NORTH_KOREA" + "\n");
                    try {
                        newLine = in.readLine();
                        if (newLine == null) return;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (newLine.contains("UNITED_KINGDOM") || newLine.contains("ITALY") || newLine.contains("NORTH_KOREA")) {
                        movie.getDirector().setCountry(Country.valueOf(newLine));
                        break;
                    }
                }
            }
            if (movie.getMoviegenre() == null) {
                while (true) {
                    System.out.println("\n" + "Переменная moviegenre - Null, либо не содержится в ENUM");
                    System.out.println("Введите одну из переменных:" + "\n"
                            + "ACTION" + "\n"
                            + "DRAMA" + "\n"
                            + "MUSICAL" + "\n"
                            + "THRILLER" + "\n"
                            + "FANTASY" + "\n");
                    try {
                        newLine = in.readLine();
                        if (newLine == null) return;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (newLine.contains("ACTION") || newLine.contains("DRAMA") || newLine.contains("MUSICAL")
                            || newLine.contains("THRILLER") || newLine.contains("FANTASY")) {
                        movie.setMoviegenre(MovieGenre.valueOf(newLine));
                        break;
                    }
                }
            }
            if (movie.getMpaaRating() == null) {
                while (true) {
                    System.out.println("\n" + "Переменная mpaarating - Null, либо не содержится в ENUM");
                    System.out.println("Введите одну из переменных:" + "\n"
                            + "G" + "\n"
                            + "PG" + "\n"
                            + "PG_13" + "\n"
                            + "R" + "\n"
                            + "NC_17" + "\n");
                    try {
                        newLine = in.readLine();
                        if (newLine == null) return;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (newLine.contains("G") || newLine.contains("PG") || newLine.contains("PG_13")
                            || newLine.contains("R") || newLine.contains("NC_17")) {
                        movie.setMoviegenre(MovieGenre.valueOf(newLine));
                        break;
                    }
                }
            }
        } else {
            this.checkEverything(movie);
        }

    }

    @Override
    public void checkEverything(Movie movie) {
        if (movie.getName() == null) {
            logger.info("Name - field is null, it will be replaced with default value");
            movie.setName("id" + movie.getId());
        }
        if (movie.getCoordinates() == null) {
            logger.info("Coordinates - field is null, it will be replaced with default value");
            movie.setCoordinates(new Coordinates(100L, 100L));
        }
        if (Long.valueOf(movie.getCoordinates().getX()) == null) {
            logger.info("XCoordinate - field is null, it will be replaced with default value");
            movie.getCoordinates().setX(100L);
        }
        if (Long.valueOf((long) movie.getCoordinates().getY()) == null) {
            logger.info("YCoordinate - field is null, it will be replaced with default value");
            movie.getCoordinates().setY(100L);
        }
        if (Long.valueOf(movie.getOscarcount()) == null) {
            logger.info("OscarCount - field is null, it will be replaced with default value");
            movie.setOscarsCount(1);
        }
        if (movie.getMoviegenre() == null) {
            logger.info("MovieGenre - field is null, it will be replaced with default value");
            movie.setMoviegenre(MovieGenre.ACTION);
        }
        if (movie.getMpaaRating() == null) {
            logger.info("MovieGenre - field is null, it will be replaced with default value");
            movie.setMpaaRating(MpaaRating.PG);
        }
        if (movie.getDirector() == null) {
            logger.info("Director - field is null, it will be replaced with default value");
            movie.setDirector(new Person("Roma", "55", Color.RED, Country.ITALY, new Location(5.0f, 5, 6.0F)));
        }
        if (movie.getDirector().getName() == null) {
            logger.info("Director name - field is null, it will be replaced with default value");
            movie.getDirector().setName("Movie" + movie.getId());
        }
        if (movie.getDirector().getPassportID() == null) {
            logger.info("Director passportID - field is null, it will be replaced with default value");
            movie.getDirector().setPassportID(String.valueOf(Math.random() * 100));
        }
        if (movie.getDirector().getCountry() == null) {
            logger.info("Director country - field is null, it will be replaced with default value");
            movie.getDirector().setCountry(Country.UNITED_KINGDOM);
        }
        if (movie.getDirector().getHairColor() == null) {
            logger.info("Director hairColor - field is null, it will be replaced with default value");
            movie.getDirector().setHairColor(Color.GREEN);
        }
        if (movie.getDirector().getLocation() == null) {
            logger.info("Director Location - field is null, it will be replaced with default value");
            movie.getDirector().setLocation(new Location(5.0f, 5, 6.0F));
        }
    }

    private void changeX(Movie movie) {
        boolean passedValue = false;
        System.out.println("Enter new X-coordinate:");

        while (!passedValue) {
            try {
                System.out.println("a");
                Long tempX = Long.parseLong(newLine = in.readLine());

                if (tempX > 0) {
                    System.out.println("a");
                    movie.getCoordinates().setX(tempX);
                    passedValue = true;

                } else {
                    System.out.println("Entered value is inappropriated. Try another one: ");
                }
            } catch (NumberFormatException e) {
                System.out.println("It is not a number");
            } catch (NoSuchElementException e) {
                System.out.println("Field will be replaced with random value");
                movie.getCoordinates().setX((long) (Math.random() * 100));
                passedValue = true;
            } catch (Exception e) {
                System.out.println("Пожалуйста примите исполнение");
                System.exit(0);
            }
        }
    }

    private void changeY(Movie movie) {
        boolean passedValue = false;
        System.out.println("Enter new X-coordinate:");

        while (!passedValue) {
            try {
                Long tempY = Long.parseLong(newLine = in.readLine());

                if (tempY > 0) {
                    movie.getCoordinates().setY(tempY);
                    passedValue = true;
                } else {
                    System.out.println("Entered value is inappropriated. Try another one: ");
                }
            } catch (NumberFormatException e) {
                System.out.println("It is not a number");
            } catch (NoSuchElementException e) {
                System.out.println("Field will be replaced with random value");
                movie.getCoordinates().setY((long) (Math.random() * 100));
                passedValue = true;
            } catch (Exception e) {
                System.out.println("Пожалуйста примите исполнение");
                System.exit(0);
            }
        }
    }
}
