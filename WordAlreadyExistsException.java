
public class WordAlreadyExistsException extends Exception{
    WordAlreadyExistsException(){
        super();
    }
    WordAlreadyExistsException(String message){
      super(message);
    }
}
