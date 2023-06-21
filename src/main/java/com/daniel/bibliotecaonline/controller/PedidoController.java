//package com.daniel.bibliotecaonline.controller;
//
//import com.daniel.bibliotecaonline.dao.IPedidoRepository;
//import com.daniel.bibliotecaonline.dto.Autor;
//import com.daniel.bibliotecaonline.dto.Pedido;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;
//
//@RestController
//@RequestMapping(path = "pedido" , produces = "application/json")
//@CrossOrigin(origins = "*" )
//public class PedidoController {
//
//    private IPedidoRepository iPedidoRepository;
//
//    public PedidoController(IPedidoRepository iPedidoRepository){
//        this.iPedidoRepository = iPedidoRepository;
//    }
//
////    @GetMapping("list")
////    public Iterable<Pedido> listAll(){
////        if (iPedidoRepository.findAll().toString() != null) {
////            return iPedidoRepository.findAll();
////        }
////        return null;
////    }
//
//    @GetMapping("/find/{pedidoId}")
//    public Optional<Pedido> findById(@PathVariable("pedidoId") String id){
//        if (iPedidoRepository.findById(id).isPresent()) return iPedidoRepository.findById(id);
//        else return null;
//    }
//
//    @PostMapping(path = "/save", consumes = "application/json")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Optional<Pedido> savePedido(@RequestBody Optional<Pedido> pedido) {
//        return iPedidoRepository.save(pedido);
//    }
//
//    @PatchMapping(path = "/update/{pedidoId}", consumes = "application/json")
//    public Optional<Pedido> updatePedido(@PathVariable("pedidoId") String pedidoId, @RequestBody Optional<Pedido> autor) {
//        return iPedidoRepository.updatePedido(iPedidoRepository, autor);
//    }
//
//    @DeleteMapping("/delete/{pedidoId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deletePedido(@PathVariable("pedidoId") String pedidoId) {
//        try {
//            iPedidoRepository.delete(pedidoId);
//        } catch (EmptyResultDataAccessException e) {
//            System.out.println("No se encontro pedido");
//        }
//    }
//}
