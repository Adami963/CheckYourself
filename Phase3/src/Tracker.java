package Phase3.src;
public class Tracker {
    private int addCount;
    private int removeCount;
    private int rem;
    private String item;
    private boolean completed;

    //DEFAULT CONSTRUCTOR
    public Tracker(){
        this.addCount = 0;
        this.removeCount = 0;
        this.rem = 0;
        this.item = " ";
        this.completed = false;
    }

    //CONSTRUCTOR W/ PARAMETERS
    public Tracker(int addCount, int removeCount, int rem, String item, Boolean completed){
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

    public int getRemoveCount(){
        return removeCount;
    }

     public int getRemainingItems(){
        //will use initCount + addItems - completed to identify what is left
        return rem;
    }

    public String getItem(){
        return item;
    }

    public boolean getCompleted(){
        //will display whether item wass completed
        return completed;
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

    //INCREMENT + 1
    public void incAdd(){
        this.addCount++;
    }
    public void incRemove(){
        this.removeCount++;
    }
    
}//end of class
