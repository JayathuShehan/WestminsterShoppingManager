import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

public class CustomerGUI extends JFrame{
    static List<Product> allProducts;

    static WestminsterShoppingManager func = new WestminsterShoppingManager(); // for user functions in there
    static JTable table;
    static JPanel productDetailPanel;

    public CustomerGUI() {
        allProducts = func.getProductsFromFile();

        Object[][] allProducts = new Object[CustomerGUI.allProducts.size()][5];
        String[] info;

// Adding Items to allProducts list
        for (int i = 0; i < CustomerGUI.allProducts.size(); i++) {
            Product product = CustomerGUI.allProducts.get(i);

            info = product.getProductDetails().split("\\s*,\\s*"); // for Info column
            // Adding items to allProducts list
            allProducts[i][0] = product.getProductID();
            allProducts[i][1] = product.getProductName();
            allProducts[i][2] = product.getCategory();
            allProducts[i][3] = product.getPrice();
            allProducts[i][4] = info[5] + ", " + info[6];
        }

// Top Panel
        int width = 300;
        int height = 60;  // Reduced height for a more compact top panel

// Select Product Category panel
        JLabel label = new JLabel("Select Product Category");
        label.setFont(new Font("Poppins", Font.BOLD, 17));

        JPanel textPanel = new JPanel();
        textPanel.setBounds(0, 0, width, height);
        textPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 5));
        textPanel.setLayout(new BorderLayout());
        textPanel.add(label);

// dropdown panel
        String[] categories = {"All", "Electronics", "Clothing"};
        JComboBox<String> dropdown = new JComboBox<>(categories);
        dropdown.setFont(new Font("Poppins", Font.BOLD, 17));

        JPanel dropDownPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        dropDownPanel.setBounds(300, 0, width, height);
        dropDownPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));  // Adjusted border
        dropDownPanel.setLayout(new BorderLayout());
        dropDownPanel.add(dropdown);

        // Add ActionListener to the JComboBox
        dropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterTableData(Objects.requireNonNull(dropdown.getSelectedItem()).toString());
            }
        });

// Open shopping cart button
        JButton shoppingCartBtn = new JButton("Shopping Cart");
        shoppingCartBtn.setFont(new Font("Poppins", Font.BOLD, 17));

        JPanel shoppingCartBtnPanel = new JPanel();
        shoppingCartBtnPanel.setBounds(600, 0, width, height);
        shoppingCartBtnPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));  // Adjusted border
        shoppingCartBtnPanel.setLayout(new BorderLayout());
        shoppingCartBtnPanel.add(shoppingCartBtn);

        // open Shopping cart
        ShoppingCartGUI shoppingCartFame = new ShoppingCartGUI();
        shoppingCartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shoppingCartFame.shoppingCartFrame();
            }
        });

// Table Panel
        String[] columnNames = {"Product ID", "Name", "Category", "Price(£)", "Info"};

        table = new JTable(allProducts, columnNames);
        table.setFont(new Font("Poppins", Font.BOLD, 14));

        int[] columnWidths = {100, 150, 100, 80, 300};
        for (int i = 0; i < columnWidths.length; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
        }

        // Create a TableRowSorter
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);


        int rowHeight = 30;
        table.setRowHeight(rowHeight);
        table.getTableHeader().setReorderingAllowed(false); // Set table column unmovable
        table.getTableHeader().setFont(new Font("Poppins", Font.BOLD, 17)); // Set table header fonts
        table.setDefaultEditor(Object.class, null);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(880, 200)); // Set preferred size for the scroll pane

        JPanel productTablePanel = new JPanel();
        productTablePanel.setBounds(10, height, 880, 200);  // Adjusted the height
        productTablePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));
        productTablePanel.setLayout(new BorderLayout());
        productTablePanel.add(scrollPane);

// Product Detail Panel
        productDetailPanel = new JPanel();
        productDetailPanel.setBounds(10, height + 200, 880, 210);  // Adjusted the height
        productDetailPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));
        productDetailPanel.setLayout(new BorderLayout());

// View Detail
        /* When click a row then the product details will appear below the table */
        ListSelectionModel selectedProduct = table.getSelectionModel();
        selectedProduct.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();

                    printSelectedRowInfo(selectedRow);
                }
            }
        });

// Add to cart button panel
        JButton addToCartBtn = new JButton("Add to Shopping Cart");

        JPanel addToCartBtnPanel = new JPanel();
        addToCartBtnPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100));
        addToCartBtnPanel.setBounds(0, height + 410, 900, 40);  // Adjusted the height
        addToCartBtnPanel.setLayout(new BorderLayout());
        addToCartBtnPanel.add(addToCartBtn);

// Create Frame
        this.setTitle("Westminster Shopping Centre");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setSize(900, height + 500);  // Adjusted the height
        this.setResizable(false);
        this.setVisible(true);

// Adding components to the frame
        this.add(textPanel);
        this.add(dropDownPanel);
        this.add(shoppingCartBtnPanel);
        this.add(productTablePanel);
        this.add(productDetailPanel);
        this.add(addToCartBtnPanel);
    }

// Filter Data by Categories
    private static void filterTableData(String selectedCategory) {
        TableRowSorter<TableModel> sorter = (TableRowSorter<TableModel>) table.getRowSorter();

        // Set up a RowFilter to filter data based on the selected category
        RowFilter<Object, Object> rowFilter;
        if (selectedCategory.equals("All")) {
            rowFilter = new RowFilter<Object, Object>() {
                public boolean include(Entry<?, ?> entry) {
                    return true; // Include all rows when "All" is selected
                }
            };
        } else {
            rowFilter = RowFilter.regexFilter(selectedCategory, 2); // Assuming "Category" is at index 2
        }

        sorter.setRowFilter(rowFilter);
    }

// print selected product
    private static void printSelectedRowInfo(int selectedRow) {
        //Add title
        JTextField title = new JTextField();
        title.setText("Selected Product - Details");
        title.setFont(new Font("Poppins", Font.BOLD, 17));
        title.setEditable(false);

        // Get the data from the selected row
        String productID = (String) table.getValueAt(selectedRow, 0); // Assuming "Product ID" is at index 0
        String productCategory = (String) table.getValueAt(selectedRow, 2);
        String productName = (String) table.getValueAt(selectedRow, 1); // Assuming "Name" is at index 1
        String information = (String) table.getValueAt(selectedRow, 4);
        String[] info =  information.split("\\s*,\\s*");
        String sizeOrBrand = "Size";
        String colourOrWarrantyPeriod = "Colour";
        int availability = 0;
        if (productCategory.equals("Electronics")) {
            sizeOrBrand = "Brand";
            colourOrWarrantyPeriod = "Warranty Period";
        }
        for (Product product : allProducts) {
            if (Objects.equals(product.getProductID(), productID)) {
                availability = product.getQuantity();
            }
        }

        // Print the information in panel1 (replace this with your actual logic)
        String productDetails = "Product ID : " + productID + "\n" +
                "Category : " + productCategory + "\n" +
                "Name : " + productName + "\n" +
                sizeOrBrand + " : " + info[0] + "\n" +
                colourOrWarrantyPeriod + " : " + info[1] + " months\n" +
                "Items Available : " + availability;

        productDetailPanel.removeAll();
        productDetailPanel.add(title);

        JTextArea detailArea = new JTextArea(productDetails);
        detailArea.setEditable(false);
        detailArea.setLineWrap(true);
        detailArea.setWrapStyleWord(true);

        productDetailPanel.add(detailArea);
        productDetailPanel.revalidate();
        productDetailPanel.repaint();
    }
}
