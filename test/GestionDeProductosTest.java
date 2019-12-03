/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Control.Verificaciones;
import Entidad.Producto;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MARIO BOHORQUEZ
 */
public class GestionDeProductosTest {
    
    public GestionDeProductosTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void Aniadir_ProductoTest() {
        String resultado = "";
        Producto producto= new Producto();
        producto.setNombre("Acpm");
        if(producto.getNombre().length()==0){
            resultado = "Todo producto debe tener un nombre";
        }else{
            if(producto.getNombre().length()<51){
                int valor;
                Verificaciones ver= new Verificaciones();
                String texto1 = "20";
                String texto2= "30";
                if(texto2.length()!=0){
                    if(texto1.length()!=0){
                        if(!ver.isNumeric(texto1)){
                            resultado = "Ingrese un numero como cantidad inicial";
                        }
                        else if (!ver.isNumeric(texto2)){
                            resultado = "Ingrese un numero como precio";
                        }
                        else if(20<texto2.length()){
                            resultado = "El número de digitos no debe ser mayor a 20";
                        }
                        else if(20<texto1.length()){
                            resultado = "El número de digitos no debe ser mayor a 20";
                        }
                        else if(texto2.length()<0){
                            resultado = "El precio no puede ser negativo";
                        }
                        else if(texto1.length()<0){
                            resultado = "La cantidad inicial de productos no puede ser negativa";
                        }
                        else if(texto1.length()==0){
                            resultado = "La cantidad inicial de productos no puede ser cero";
                        }
                        else{
                            producto.setCantidad(Integer.parseInt(texto1));
                            producto.setValor(Integer.parseInt(texto2));
                            producto.setCategoria("Combustible");
                            resultado = ver.AddProductoNuevo(producto);
                        }
                    }else{
                        resultado = "Todo producto debe tener una cantidad inicial de unidades";
                    }
                }else{
                    resultado = "Todo producto debe tener un precio";
                }
            }else{
                resultado = "El nombre del producto no puede superar los 50 caracteres";
            }
        }
        
        assertEquals(resultado,"Nuevo producto ingresado exitosamente!");
    }

    @Test
    public void Aniadir_existenciasTest(){
        Verificaciones ver= new Verificaciones();
        String resultado = "";
        String text= "1";
        String text2= "99";
        
        if(text.length()==0){
            resultado = "Es necesaria una ID válida para ésta operación";
        }else if(!ver.isNumeric(text)){
            resultado = "La ID debe ser un número";
        }else if(20<text.length()){
            resultado = "La ID no puede superar los 20 dígitos";
        }else if(!ver.VerificarExistenciaID(Integer.parseInt(text))){
            resultado = "No existe ningún producto registrado con ésta ID";
        }else{
            if(text2.length()==0){
                resultado = "No se ha agregado ninguna cantidad";
            }else if(!ver.isNumeric(text2)){
                resultado = "Ingrese un número como cantidad nueva";
            }else if(20<text2.length()){
                resultado = "El número de digitos no puede ser mayor a 20";
            }else if(Integer.parseInt(text2)<0){
                resultado = "La cantidad nueva de productos no puede ser negativa";
            }else{
                Producto producto= new Producto();
                producto.setId(Integer.parseInt(text));
                int a= ver.pdao.leerID(producto).getCantidad();
                int b= a+Integer.parseInt(text2);
                ver.pdao.actualizarCANT(producto, b);
                resultado = "Existencias añadidas"; 
            }
        }   
        assertEquals(resultado,"Existencias añadidas");
    }
    
    @Test
    public void Quitar_ExistenciasTest(){
        Verificaciones ver= new Verificaciones();
        String resultado;
        String text= "1";
        String text2= "2";
        
        if(text.length()==0){
            resultado="Es necesaria una ID válida para ésta operación";
        }else if(!ver.isNumeric(text)){
            resultado="La ID debe ser un número";
        }else if(20<text.length()){
            resultado ="La ID no puede superar los 20 dígitos";
        }else if(!ver.VerificarExistenciaID(Integer.parseInt(text))){
            resultado="No existe ningún producto registrado con ésta ID";
        }else{
            if(text2.length()==0){
                resultado="No se ha agregado ninguna cantidad";
            }else if(!ver.isNumeric(text2)){
                resultado="Ingrese un número como cantidad nueva";
            }else if(20<text2.length()){
                resultado="El número de digitos no puede ser mayor a 20";
            }else if(Integer.parseInt(text2)<0){
                resultado = "La cantidad nueva de productos no puede ser negativa";
            }else{
                Producto producto= new Producto();
                    producto.setId(Integer.parseInt(text));
                    int a= ver.pdao.leerID(producto).getCantidad();
                    if(a>=Integer.parseInt(text2)){
                        int b=a-Integer.parseInt(text2);
                        ver.pdao.actualizarCANT(producto, b);
                        resultado="Existencias eliminadas";
                    }
                    else{
                        resultado="No hay suficientes existencias para ser eliminadas";
                    } 
            }
        }
        assertEquals(resultado,"Existencias eliminadas");
    }
    
    @Test
    public void EditarProductoTest(){
    Verificaciones ver= new Verificaciones();
        String resultado;
        String text= "1";
        String text2= "40";
        String text3= "Corriente";
        
        if(text.length()==0){
            resultado = "Es necesaria una ID válida para ésta operación";
        }else if(!ver.isNumeric(text)){
            resultado ="La ID debe ser un número";
        }else if(20<text.length()){
            resultado ="La ID no puede superar los 20 dígitos";
        }else if(!ver.VerificarExistenciaID(Integer.parseInt(text))){
            resultado = "No existe ningún producto registrado con ésta ID";
        }else{
            if(text2.length()==0 && text3.length()!=0){
                if(50<text3.length()){
                    resultado ="El nombre del producto no puede superar los 50 caracteres";
                }else{
                    Producto productoconlaID= new Producto();
                    productoconlaID.setId(Integer.parseInt(text));
                    Producto productoanterior = ver.pdao.leerID(productoconlaID);
                    ver.pdao.actualizarEDIT(productoanterior,text3,productoanterior.getValor());
                    resultado="Producto actualizado";
                }
            }else if(text2.length()!=0 && text3.length()==0){
                if(!ver.isNumeric(text2)){
                    resultado="Ingrese un número como precio";
                }else if(20<text2.length()){
                    resultado="El número de dígitos no puede ser mayor que 20";
                }else if(Integer.parseInt(text2)<0){
                    resultado="El precio no puede ser negativo";
                }else{
                    Producto productoconlaID= new Producto();
                    productoconlaID.setId(Integer.parseInt(text));
                    Producto productoanterior = ver.pdao.leerID(productoconlaID);
                    ver.pdao.actualizarEDIT(productoanterior,productoanterior.getNombre(),Integer.parseInt(text2));
                    resultado="Producto actualizado";
                }
            }else if(text2.length()!=0 && text3.length()!=0){
                if(50<text3.length()){
                    resultado="El nombre del producto no puede superar los 50 caracteres";
                }else if(!ver.isNumeric(text2)){
                    resultado="Ingrese un número como precio";
                }else if(20<text2.length()){
                    resultado="El número de dígitos no puede ser mayor que 20";
                }else if(Integer.parseInt(text2)<0){
                    resultado= "El precio no puede ser negativo";
                }else{
                    Producto productoconlaID= new Producto();
                    productoconlaID.setId(Integer.parseInt(text));
                    Producto productoanterior = ver.pdao.leerID(productoconlaID);
                    ver.pdao.actualizarEDIT(productoanterior,text3,Integer.parseInt(text2));
                    resultado="Producto actualizado";
                }
            }else{
                resultado="No se ha cambiado ningún elemento";
            }
        }      
        assertEquals(resultado,"Producto actualizado");
    }
   
    
    
    @Test
    public void QuitarProductoTest(){
        String resultado;
        Verificaciones ver= new Verificaciones();
        String text= "1";
        if(text.length()!=0){
            if(ver.isNumeric(text)){
                if(text.length()<20){
                    if(ver.VerificarExistenciaID(Integer.parseInt(text))){
                        Producto p= new Producto();
                        p.setId(Integer.parseInt(text));
                        ver.pdao.eliminar(p);
                        resultado="Producto Eliminado con éxito";
                    }
                    else{
                        resultado="No existe ningún producto registrado con ésta ID";
                    }
                }
                else{
                    resultado ="La ID no puede superar los 20 dígitos";
                }
            }
            else{
                resultado="La ID debe ser un número";
            }
        }
        else{
            resultado="Es necesaria una ID válida para ésta operación";
        }
        assertEquals(resultado,"Producto Eliminado con éxito");
    }
}
