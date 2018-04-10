package view;



import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class QuizzViewTextController {
	
	@FXML
	private Label question;
	@FXML
	private Label rep1;
	@FXML
	private Label rep2;
	@FXML
	private Label rep3;
	@FXML
	private Label rep4;
	@FXML
	private Label intitule;
	



    // Reference to the main application.
    private Main main;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public QuizzViewTextController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        
        
    }
    
    @FXML
    private void rep1() {
    	question.setText("ezp1");
    }
    
    @FXML
    private void rep2() {
    	question.setText("rep2");
    }
    
    @FXML
    private void rep3()  {
    	question.setText("rep3");
    }
    
    @FXML
    private void rep4()  {
    	question.setText("rep4");
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(Main main) {
        this.main = main;
    }

}
