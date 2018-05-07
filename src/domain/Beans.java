/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author karol
 */
public class Beans {

    String width;
    
public Beans(){}
    public Beans(String width) {
        this.width = width;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "Beans3{" + "width=" + width + '}';
    }
    public int size(){
     
        return this.getWidth().length()*2;
    
    }


    
}
