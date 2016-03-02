package exceptions;

/**
 * Created by Omer on 12/24/2015.
 */
public class GameEngineException extends RuntimeException {

    private static final String LOG_HEADER = "EnJine: ";

    public GameEngineException(String message){
        super(LOG_HEADER + message);
    }
}
