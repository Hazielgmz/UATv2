package Controlador;

import Vista.VAdmin;
import Vista.VPOSMenu;
import Vista.VPago;

import javax.swing.*;

import DAO.DProducto;
import Modelo.MProducto;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.List;

public class CPOSMenu {
    private final VPOSMenu view;
    private double orderTotal;

    public CPOSMenu(VPOSMenu view) {
        this.view = view;
        this.orderTotal = 0.00;
    }

    public List<MProducto> getProductosPorTipo(String tipo) {
        DProducto dao = new DProducto();
        return dao.obtenerProductosPorTipo(tipo); // Llamada al método en la capa DAO
    }
    
    public ActionListener createDynamicButtonListener(MProducto producto) {
        return e -> {
            String input = JOptionPane.showInputDialog(view, "Ingrese el peso en kg de " + producto.getNombreProducto() + ":");
            if (input == null || input.trim().isEmpty()) { // Validar entrada nula o vacía
                JOptionPane.showMessageDialog(view, "Por favor, ingrese un valor válido para el peso.");
                return;
            }
            try {
                double weight = Double.parseDouble(input.trim());
                double totalPrice = weight * producto.getCosto().doubleValue();

                view.getProductListModel().addElement(producto.getNombreProducto() + " (" + weight + " kg) - $" + String.format("%.2f", totalPrice));
                orderTotal += totalPrice;
                view.getOrderTotalLabel().setText("Order Total: $" + String.format("%.2f", orderTotal));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Por favor, ingrese un valor numérico válido para el peso.");
            }
        };
    }
    

    public void openMoreProductsDialog() {
        List<MProducto> productosGR = getProductosPorTipo("GR"); // Obtener productos de tipo "GR"
    
        if (productosGR.isEmpty()) {
            JOptionPane.showMessageDialog(view, "No hay productos disponibles con el tipo 'GR'.");
            return;
        }
    
        // Crear el panel para la lista de productos
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.add(new JLabel("Seleccione un producto:"), BorderLayout.NORTH);
    
        // Lista de productos
        DefaultListModel<String> listModel = new DefaultListModel<>();
        productosGR.forEach(p -> listModel.addElement(p.getNombreProducto()));
        JList<String> productList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(productList);
        panel.add(scrollPane, BorderLayout.CENTER);
    
        // Mostrar cuadro de diálogo
        int option = JOptionPane.showConfirmDialog(view, panel, "Seleccionar Producto", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    
        if (option == JOptionPane.OK_OPTION) {
            // Obtener el producto seleccionado
            String selectedProduct = productList.getSelectedValue();
            if (selectedProduct != null) {
                MProducto productoSeleccionado = productosGR.stream()
                        .filter(p -> p.getNombreProducto().equals(selectedProduct))
                        .findFirst()
                        .orElse(null);
    
                if (productoSeleccionado != null) {
                    createDynamicButtonListener(productoSeleccionado).actionPerformed(null);
                }
            } else {
                JOptionPane.showMessageDialog(view, "No seleccionaste ningún producto.");
            }
        }
    }
    
    
    public void searchProductByBarcode() {
    String barcode = JOptionPane.showInputDialog(view, "Ingrese el número de código de barras:");
    
    if (barcode != null && !barcode.isEmpty()) {
        // Buscar el producto en la base de datos
        DProducto dao = new DProducto();
        MProducto producto = dao.buscarProductoPorCodigoBarras(barcode);
        
        if (producto != null) {
            // Mostrar los detalles del producto y actualizar la lista de productos
            String productName = producto.getNombreProducto();
            double productPrice = producto.getCosto().doubleValue();
            
            view.getProductListModel().addElement(productName + " - $" + String.format("%.2f", productPrice));
            orderTotal += productPrice;
            
            view.getOrderTotalLabel().setText("Order Total: $" + String.format("%.2f", orderTotal));
        } else {
            JOptionPane.showMessageDialog(view, "Producto no encontrado. Verifique el código de barras.");
        }
    } else {
        JOptionPane.showMessageDialog(view, "Por favor, ingrese un código de barras válido.");
    }
}


public void continueSale() {
    if (view.getProductListModel().isEmpty()) {
        JOptionPane.showMessageDialog(view, "No hay productos en la venta.");
        return;
    }
        // Crear el texto del carrito basado en los productos seleccionados
        StringBuilder carritoProductos = new StringBuilder();
        for (int i = 0; i < view.getProductListModel().size(); i++) {
            carritoProductos.append(view.getProductListModel().getElementAt(i)).append("\n");
        }

        // Obtener el total de la orden
        double orderTotal = Double.parseDouble(view.getOrderTotalLabel().getText().replace("Order Total: $", ""));
        // Asegúrate de tener una referencia a CCorteCaja
    CCorteCaja corteCaja = new CCorteCaja(500.0, 0.0, 0.0); // Esto debería ser compartido en la aplicación
    // Crear instancia de VPago y pasar los datos
    VPago vPago = new VPago(orderTotal, this,corteCaja); // Pasamos el total y el controlador actual
    vPago.actualizarCarrito(carritoProductos.toString()); // Actualizar el carrito de VPago
    vPago.setVisible(true); // Mostrar la ventana de pago

    view.setVisible(false); // Oculta la ventana del menú principal
}

    public void removeSelectedProduct() {
        int selectedIndex = view.getProductList().getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedProduct = view.getProductListModel().getElementAt(selectedIndex);
            String[] productDetails = selectedProduct.split(" - \\$");
            double price = Double.parseDouble(productDetails[1]);
            orderTotal -= price;
            view.getOrderTotalLabel().setText("Order Total: $" + String.format("%.2f", orderTotal));
            view.getProductListModel().remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(view, "Selecciona un producto para eliminar.");
        }
    }

    public void clearCart() {
        view.getProductListModel().clear();
        orderTotal = 0.00;
        view.getOrderTotalLabel().setText("Order Total: $0.00");
        JOptionPane.showMessageDialog(view, "El carrito ha sido vaciado.");
    }

    public void abrirAdminView() {
    	 // Solicitar la contrasena al usuario
        String password = JOptionPane.showInputDialog(view, "Ingrese la contraseña para acceder a la administracion:");

        // Verificar la contrase�a
        if ("3312".equals(password)) {
            // Contrase�a correcta: abrir la ventana de administraci�n
            VAdmin adminView = new VAdmin();
            adminView.setVisible(true); // Mostrar la ventana de administraci�n
            view.setVisible(false); // Oculta la ventana actual del men�
            
        } else {
            // Contrase�a incorrecta: mostrar mensaje de error
            JOptionPane.showMessageDialog(view, "Contraseña incorrecta. Intentelo nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void lockPOS() {
        String password = JOptionPane.showInputDialog(view, "Ingrese la contraseña para desbloquear:");
        if ("3312".equals(password)) {
            JOptionPane.showMessageDialog(view, "Punto de venta desbloqueado.");
        } else {
            JOptionPane.showMessageDialog(view, "Contraseña incorrecta. Intentelo nuevamente.");
            lockPOS();
        }
    }

    
    public VPOSMenu getView() {
        return view;
    }
}
