// Copied from repo: https://github.com/apache/struts-examples.git
// file: annotations/src/main/java/example/actions/RegisterAction.java
// rev: heads/master-0-g371e480
package example.actions;

import com.opensymphony.xwork2.ActionSupport;
import example.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;

/**
 * Acts as a controller to handle actions
 * related to registering a user.
 *
 * @author bruce phillips
 */
// ok: convention-annotation-class-method
public class RegisterAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    private static final Logger log = LogManager.getLogger(RegisterAction.class);

    private Person personBean;

    // ruleid: convention-annotation-class-method
    @Action("register-input")
    public String input() throws Exception {

        log.info("In input method of class RegisterAction");

        return INPUT;
    }

    // ok: convention-annotation-class-method
    public String execute() throws Exception {

        log.info("In execute method of class RegisterAction");

        //call Service class to store personBean's state in database

        return SUCCESS;
    }


    public Person getPersonBean() {
        return personBean;
    }

    public void setPersonBean(Person person) {
        personBean = person;
    }

}

// ruleid: convention-annotation-class-method
@Action("blah")
public class RegisterAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    private static final Logger log = LogManager.getLogger(RegisterAction.class);

    private Person personBean;

    // ruleid: convention-annotation-class-method
    @Actions("register-input")
    public String input() throws Exception {

        log.info("In input method of class RegisterAction");

        return INPUT;
    }

    // ok: convention-annotation-class-method
    public String execute() throws Exception {

        log.info("In execute method of class RegisterAction");

        //call Service class to store personBean's state in database

        return SUCCESS;
    }


    public Person getPersonBean() {
        return personBean;
    }

    public void setPersonBean(Person person) {
        personBean = person;
    }

}
