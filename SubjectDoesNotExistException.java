public class SubjectDoesNotExistException extends Exception{
    String subject;

    public SubjectDoesNotExistException(String subject)
    {
        this.subject = subject;
    }

    public String toString()
    {
        return this.getClass().getName() + ": There is no subject \""+subject+ "\"";
    }

}
