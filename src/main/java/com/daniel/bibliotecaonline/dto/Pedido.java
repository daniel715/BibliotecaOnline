package com.daniel.bibliotecaonline.dto;

import java.util.Date;

public class Pedido {

    private String idPedido;
    private String nombreComprador;
    private String telefonoComprador;
    private String direccionEntrega;
    private String direccionIp;
    private String appPago;
    private Date fechaPedido;
    private Float valorCompra;
    private String respuestaPago;
    private String status;


    public Pedido(String idPedido, String nombreComprador, String telefonoComprador, String direccionEntrega, String direccionIp, String appPago, Date fechaPedido, Float valorCompra, String respuestaPago, String status) {
        this.idPedido = idPedido;
        this.nombreComprador = nombreComprador;
        this.telefonoComprador = telefonoComprador;
        this.direccionEntrega = direccionEntrega;
        this.direccionIp = direccionIp;
        this.appPago = appPago;
        this.fechaPedido = fechaPedido;
        this.valorCompra = valorCompra;
        this.respuestaPago = respuestaPago;
        this.status = status;
    }

    public Pedido() {

    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public String getNombreComprador() {
        return nombreComprador;
    }

    public void setNombreComprador(String nombreComprador) {
        this.nombreComprador = nombreComprador;
    }

    public String getTelefonoComprador() {
        return telefonoComprador;
    }

    public void setTelefonoComprador(String telefonoComprador) {
        this.telefonoComprador = telefonoComprador;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public String getDireccionIp() {
        return direccionIp;
    }

    public void setDireccionIp(String direccionIp) {
        this.direccionIp = direccionIp;
    }

    public String getAppPago() {
        return appPago;
    }

    public void setAppPago(String appPago) {
        this.appPago = appPago;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Float getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(Float valorCompra) {
        this.valorCompra = valorCompra;
    }

    public String getRespuestaPago() {
        return respuestaPago;
    }

    public void setRespuestaPago(String respuestaPago) {
        this.respuestaPago = respuestaPago;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
