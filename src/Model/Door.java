
package Model;
import Model.Questions.Question;

import java.io.Serial;
import java.io.Serializable;

public class Door implements Serializable {
    @Serial
    private static final long serialVersionUID = -6432147852365234569L;
        private final Question myQuestion ;
        // represent door status, 0 if not tired, 1 if answered correctly, 2 if locked
        // 3 is used by recursive maze solver algorithm
        private int myDoorState;
        public Door() throws Exception {
            myQuestion = new Question();
            myDoorState = 0; //not attempted
        }
        private boolean validateState(final int theState) {
            return theState >= 0 && theState <= 3;
        }
        public Question getMyQuestion(){
            return myQuestion;
        }
        public int getDoorState() {
            return myDoorState;
        }

        public void setDoorState(final int theState) {
            if(validateState(theState)) {
                myDoorState = theState;
            }
        }
    }





