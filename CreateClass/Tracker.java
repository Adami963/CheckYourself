public class Tracker {
    int addCount;
    int removeCount;
    int rem;
    String item;
    boolean completed;

    //DEFAULT CONSTRUCTOR
    Tracker(){
        addCount = 0;
        removeCount = 0;
        rem = 0;
        item = " ";
        completed = false;
    }

    //CONSTRUCTOR W/ PARAMETERS
    Tracker(int addCount, int removeCount, int rem, String item, Boolean completed){
        this.addCount = addCount;
        this.removeCount = removeCount;
        this.rem = rem;
        this.item = item;
        this.completed = completed;
    }
    //GETTERS
    public int getAddCount(){
        //will count the initial number of assignments we began with
        return addCount;
    }

    public boolean getCompleted(){
        //will display whether item wass completed
        return false;
    }

    public int getRemainingItems(int rem){
        //will use initCount + addItems - completed to identify what is left
        return rem;
    }
    public String getItem(){
        return item;
    }
    
    //SETTERS
    public void setItems(String item){
        this.item = item;
    }
    public void setAddCount(int addCount){
        this.addCount = addCount;
    }
    public void removeCount(int removeCount){
        this.removeCount = removeCount;
    }

    public void setRemain(int rem){
        this.rem = rem;
    }
    public void setCompleted(boolean completed){
        this.completed = completed;
    }
    
}//end of class
