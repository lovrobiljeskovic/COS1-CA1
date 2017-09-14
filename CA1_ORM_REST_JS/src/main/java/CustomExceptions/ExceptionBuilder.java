/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomExceptions;

import javax.ws.rs.ext.Provider;

/**
 *
 * @author Dell
 */
public class ExceptionBuilder extends RuntimeException
{
    public ErrorMessageBuilder q;

    /**
     * Creates a new instance of <code>NotFoundException</code> without detail
     * message.
     */
    public ExceptionBuilder()
    {
        
    }

    /**
     * Constructs an instance of <code>NotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExceptionBuilder(String msg)
    {
        super(msg);
    
    }
   
    public ExceptionBuilder (ErrorMessageBuilder q)
    {
        this.q = q;
        
    }

    public ErrorMessageBuilder getQ()
    {
        return q;
    }

    public void setQ(ErrorMessageBuilder q)
    {
        this.q = q;
    }
    
    
}
