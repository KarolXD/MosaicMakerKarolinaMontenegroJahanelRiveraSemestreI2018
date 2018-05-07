/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;



import domain.Beans;
import java.io.*;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 *
 * @author karol
 */
public class MosaicFile {

    private RandomAccessFile randonAcessFile;
    private int regsQuantity;//cantidad del registro en el txt
    private int regSize;//tamno del regitro
    private String myFilePath;//rut

    private Beans beans3;

    public MosaicFile(File file) throws IOException {
        try {
            //almaceno la rut
            myFilePath = file.getPath();
            //indico el taman maximo
            this.regSize = 140;//tamño cada registro

            if (file.exists() && !file.isFile()) {///
                throw new IOException(file.getName() + " is an invalid file");
            } else {
                randonAcessFile = new RandomAccessFile(file, "rw");//leer y escribir archivo
                //se redondea hacia arriba siempre
                this.regsQuantity
                        = (int) Math.ceil((double) randonAcessFile.length() / (double) regSize);
            }
        } catch (IOException e) {
            System.out.println("Error 1, CarFile" + e);
        }

    }

    public void close() throws IOException {
        try {
            randonAcessFile.close();
        } catch (IOException e) {

        }
    }

    public int fileSize() {///Nos devuelve la cantidad de autos registrados, que tenemos en el archivo
        return this.regsQuantity;

    }

    public boolean putValue(int position, Beans beans) throws IOException {//Este metodo lo que hace es guardar la informacion,  en el objeto
        //try{ 
        if (position < 0 && position > this.regsQuantity) {

            return false;
        } else {
            if (beans.size() > this.regSize) {

                return false;

            } else {

                randonAcessFile.seek(position * this.regSize);

                randonAcessFile.writeUTF((beans.getWidth()));

                return true;
            }
        }

    }

    public boolean addEndRecord(Beans beans) throws IOException {//Este metodo nos permite escribir  en el archivo

        boolean sucess = putValue(this.regsQuantity, beans);
        try {
            if (sucess) {
                this.regsQuantity++;
            }
        } catch (Exception e) {

        }
        return sucess;
    }
    //de aqui pra arriba esta bien 

    public Beans getRead(int position) throws IOException {//este metodo se encarga de extraer la posicion que deseamos obtener
        Beans car = new Beans();
        try {

            if (position >= 0 && position <= randonAcessFile.length()) {
                randonAcessFile.seek(position * this.regSize);
                car.setWidth(randonAcessFile.readUTF());

            } else {

                return null;

            }
        } catch (IOException e) {

        }
        return car;

    }

    ///RETURN LISt
    public ArrayList<Beans> getAllStudents() throws IOException {///Este metodo se encarga de guardar todo el registro de campos de texto, en un ArrayList
        ArrayList<Beans> carArray = new ArrayList<Beans>();
        try {
            for (int i = 0; i < this.regsQuantity; i++) {
                Beans car = this.getRead(i);
                if (car != null) {
                    carArray.add(car);
                }
            }
        } catch (IOException e) {

        }
        return carArray;

    }

}//end class

