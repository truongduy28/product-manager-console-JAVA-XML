import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static File db = new File("H:\\Can-Tho-universiry-Technology\\JAVA-XML___[DangTrungTin]\\TH\\b11\\src\\QLSP1.xml");
    static List<sanpham> dssp = new ArrayList<>();
    static List<loai> dsloai = new ArrayList<>();


    public static void main(String[] args) {
        start();
    }

    public static void start() {
        boolean isLoop = true;
        while (isLoop) {
            System.out.println("*********************************");
            System.out.println("** 1/ Hiện danh sách           **");
            System.out.println("** 2/ Thêm                     **");
            System.out.println("** 3/ Sửa thong tin            **");
            System.out.println("** 4/ Xóa                      **");
            System.out.println("** 0/ Thoát                    **");
            System.out.println("*********************************");

            System.out.println("Your select: ");
            int select = scanner.nextInt();

            switch (select) {
                case 1 -> displayMenu();
                case 2 -> addMenu();
                case 3 -> editMenu();
                case 4 -> removeMenu();
                case 0 -> System.exit(0);
                default -> System.out.println("ERRR");
            }

            System.out.println("Press 1 to go back menu or 0 to exit");
            int isContinute = scanner.nextInt();
            if (isContinute == 0) {
                isLoop = false;
                return;
            }
        }
    }


    public static void displayMenu() {
        System.out.println("*********************************");
        System.out.println("** 1/ Hiện danh sách sản phẩm  **");
        System.out.println("** 2/ Hiện danh sách loại sản phẩm");
        System.out.println("*********************************");

        int option = scanner.nextInt();

        switch (option) {
            case 1:
                readProductsXML();
                printProductList(dssp);
                break;
            case 2:
                readListXML();
                printListType(dsloai);
                break;
            default:
                break;
        }
    }

    public static void addMenu() {
        System.out.println("*********************************");
        System.out.println("** 1/ Thêm sản phẩm            **");
        System.out.println("** 2/ Thêm loại sản phẩm       **");
        System.out.println("*********************************");

        int option = scanner.nextInt();

        switch (option) {
            case 1 -> insertProduct();
            case 2 -> insertProductCategory();
            default -> {
            }
        }
    }

    public static void editMenu() {
        System.out.println("*********************************");
        System.out.println("** 1/ Sửa sản phẩm             **");
        System.out.println("** 2/ Sửa loại sản phẩm        **");
        System.out.println("*********************************");

        int option = scanner.nextInt();

        switch (option) {
            case 1:
                editProduct();
                break;
            case 2:
                editCategory();
                break;
            default:
                break;
        }
    }

    public static void removeMenu() {
        System.out.println("*********************************");
        System.out.println("** 1/ Xóa sản phẩm             **");
        System.out.println("** 2/ Xóa loại sản phẩm        **");
        System.out.println("*********************************");

        int option = scanner.nextInt();

        switch (option) {
            case 1 -> removeProduct();
            case 2 -> removeCategory();
            default -> {
            }
        }
    }


    //read product data from xml
    public static void readProductsXML() {
        dssp.clear();
        try {
            DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbbuider = dbfactory.newDocumentBuilder();
            Document doc = dbbuider.parse(db);
            NodeList listProducts = doc.getElementsByTagName("sanpham");

//            System.out.println(listProducts.getLength());
            for (int i = 0; i < listProducts.getLength(); i++) {
                sanpham sp = new sanpham();
                Node n = listProducts.item(i);
                Element e = (Element) n;

                sp.setMasp(e.getAttribute("masp"));
                sp.setTensp(e.getElementsByTagName("tensp").item(0).getTextContent());
                sp.setGia(e.getElementsByTagName("gia").item(0).getTextContent());
                sp.setMaloai(e.getAttribute("maloai"));
                sp.setNuocsx(e.getElementsByTagName("nuocsx").item(0).getTextContent());

                dssp.add(sp);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    public static void printProductList(List<sanpham> ds) {
        for (int i = 0; i < ds.size(); i++) {
            System.out.println("san pham thu " + (i + 1));
            ds.get(i).printInfo();
        }
    }

    //read product category data from xml
    public static void readListXML() {
        dsloai.clear();
        try {
            DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbbuider = dbfactory.newDocumentBuilder();
            Document doc = dbbuider.parse(db);
            NodeList listType = doc.getElementsByTagName("loaisp");
            for (int i = 0; i < listType.getLength(); i++) {
                loai lsp = new loai();
                Node n = listType.item(i);
                Element e = (Element) n;
                lsp.setMaloai(e.getAttribute("maloai"));
                lsp.setTenloai(e.getElementsByTagName("tenloai").item(0).getTextContent());
                dsloai.add(lsp);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void printListType(List<loai> ds) {
        for (int i = 0; i < ds.size(); i++) {
            System.out.println("Loai san pham thu " + (i + 1));
            ds.get(i).printInfo();
        }
    }

    //insert product to xml file
    public static void insertProduct() {
        try {
            scanner.nextLine();
            DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbbuider = dbfactory.newDocumentBuilder();
            Document doc = dbbuider.parse(db);

            System.out.print("nhap ma san pham:");
            String masp = scanner.nextLine();
            System.out.print("nhap ten san pham:");
            String tensp = scanner.nextLine();
            System.out.print("gia:");
            Double gia = scanner.nextDouble();
            System.out.print("maloai:");
            scanner.nextLine();
            String maloai = scanner.nextLine();
            System.out.print("nuocsx:");
            String nuocsx = scanner.nextLine();

            NodeList listProduct = doc.getElementsByTagName("DSSP");
            Element currentProductElement = (Element) listProduct.item(0);
            Element productElement = doc.createElement("sanpham");

            Element tenspElement = doc.createElement("tensp");
            tenspElement.setTextContent(tensp);
            Element giaElement = doc.createElement("gia");
            giaElement.setTextContent(String.valueOf(gia));
            Element nuocsxElement = doc.createElement("nuocsx");
            nuocsxElement.setTextContent(nuocsx);

            Attr maspAtt = doc.createAttribute("masp");
            Attr maloaiAtt = doc.createAttribute("maloai");
            maspAtt.setValue(masp);
            maloaiAtt.setValue(maloai);
            productElement.setAttributeNode(maspAtt);
            productElement.setAttributeNode(maloaiAtt);
            productElement.appendChild(tenspElement);
            productElement.appendChild(giaElement);
            productElement.appendChild(nuocsxElement);

            currentProductElement.appendChild(productElement);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(db);
            transformer.transform(source, result);
            readProductsXML();
            System.out.println("New elements have been inserted successfully!");
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    //insert product category to xml file
    public static void insertProductCategory() {
        try {
            scanner.nextLine();
            DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbbuider = dbfactory.newDocumentBuilder();
            Document doc = dbbuider.parse(db);

            System.out.print("nhap ma loai:");
            String maloai = scanner.nextLine();
            System.out.print("nhap ten loai:");
            String tenloai = scanner.nextLine();

            NodeList listProductCategory = doc.getElementsByTagName("DSLoaiSP");
            Element currentProductCategoryElement = (Element) listProductCategory.item(0);
            Element productCategoryElement = doc.createElement("loaisp");

            Element tenloaiElement = doc.createElement("tenloai");
            tenloaiElement.setTextContent(tenloai);

            Attr maloaiAtt = doc.createAttribute("maloai");
            maloaiAtt.setValue(maloai);
            productCategoryElement.setAttributeNode(maloaiAtt);
            productCategoryElement.appendChild(tenloaiElement);

            currentProductCategoryElement.appendChild(productCategoryElement);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(db);
            transformer.transform(source, result);
//            readProductCategoriesXML();
            System.out.println("New element has been inserted successfully!");
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    //change product info and update to xml file
    public static void editProduct() {
        try {
            scanner.nextLine();
            DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbbuider = dbfactory.newDocumentBuilder();
            Document doc = dbbuider.parse(db);

            System.out.print("Nhap ma san pham muon sua thong tin: ");
            String masp = scanner.nextLine();

            NodeList productList = doc.getElementsByTagName("sanpham");

            // search for the product with the given masp
            for (int i = 0; i < productList.getLength(); i++) {
                Element productElement = (Element) productList.item(i);
                String currentMasp = productElement.getAttribute("masp");
                if (currentMasp.equals(masp)) {
                    // found the product, ask for new information
                    System.out.print("Nhap ten san pham moi: ");
                    String tensp = scanner.nextLine();
                    System.out.print("Nhap gia moi: ");
                    String giaInput = scanner.nextLine();
                    Double gia = null;
                    if (!giaInput.isEmpty()) {
                        gia = Double.parseDouble(giaInput);
                    }
                    System.out.print("Nhap nuoc san xuat moi: ");
                    String nuocsx = scanner.nextLine();
                    System.out.print("Nhap ma loai: ");
                    String maloai = scanner.nextLine();

                    // update the product information
                    if (!tensp.isEmpty()) {
                        productElement.getElementsByTagName("tensp").item(0).setTextContent(tensp);
                    }

                    if (gia != null) {
                        productElement.getElementsByTagName("gia").item(0).setTextContent(String.valueOf(gia));
                    }

                    if (!nuocsx.isEmpty()) {
                        productElement.getElementsByTagName("nuocsx").item(0).setTextContent(nuocsx);
                    }
                    if (!maloai.isEmpty()) {
                        productElement.removeAttribute("maloai"); // get the current value of maloai
                        productElement.setAttribute("maloai", maloai);
                    }

                    // save the updated information to the XML file
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource source = new DOMSource(doc);
                    StreamResult result = new StreamResult(db);
                    transformer.transform(source, result);

                    System.out.println("Thong tin san pham da duoc cap nhat thanh cong!");
                    return;
                }
            }

            // if the product was not found
            System.out.println("Khong tim thay san pham co ma " + masp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //change product category info and update to xml file
    public static void editCategory() {
        try {
            scanner.nextLine();
            DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbbuider = dbfactory.newDocumentBuilder();
            Document doc = dbbuider.parse(db);

            System.out.print("Nhap ma loai san pham muon sua thong tin: ");
            String maloai = scanner.nextLine();

            NodeList categoryList = doc.getElementsByTagName("loaisp");

            // search for the category with the given maloai
            for (int i = 0; i < categoryList.getLength(); i++) {
                Element categoryElement = (Element) categoryList.item(i);
                String currentMaloai = categoryElement.getAttribute("maloai");
                if (currentMaloai.equals(maloai)) {
                    // found the category, ask for new information
                    System.out.print("Nhap ten loai san pham moi: ");
                    String tenloai = scanner.nextLine();

                    // update the category information
                    if (!tenloai.isEmpty()) {
                        categoryElement.getElementsByTagName("tenloai").item(0).setTextContent(tenloai);
                    }

                    // save the updated information to the XML file
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource source = new DOMSource(doc);
                    StreamResult result = new StreamResult(db);
                    transformer.transform(source, result);

                    System.out.println("Thong tin loai san pham da duoc cap nhat thanh cong!");
                    return;
                }
            }

            // if the category was not found
            System.out.println("Khong tim thay loai san pham co ma " + maloai);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //remove product
    public static void removeProduct() {

        scanner.nextLine();

        try {
            DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbbuider = dbfactory.newDocumentBuilder();
            Document doc = dbbuider.parse(db);
            System.out.print("Nhap ma san pham muon xóa: ");
            String masp = scanner.nextLine();

            NodeList productElement = doc.getElementsByTagName("sanpham");

            boolean isFound = false;
            for (int i = 1; i <= productElement.getLength(); i++) {
                Element product = (Element) productElement.item(i);

                if (product.getAttribute("masp").equals(masp)) {
                    Node parent = product.getParentNode();
                    parent.removeChild(product);
                    isFound = true;
                    break;
                }
            }

            if (isFound) {
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(db);
                transformer.transform(source, result);
                System.out.print("Remove successfully!");
            } else {
                System.out.print("Not found product ");
            }
            System.out.print("");
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    //    remove category
    public static void removeCategory() {
        scanner.nextLine();
        try {
            DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbbuider = dbfactory.newDocumentBuilder();
            Document doc = dbbuider.parse(db);
            System.out.print("Nhap ma loai san pham muon xóa: ");
            String maloai = scanner.nextLine();

            NodeList categoryElement = doc.getElementsByTagName("loaisp");

            boolean isFound = false;
            for (int i = 0; i < categoryElement.getLength(); i++) {
                Element category = (Element) categoryElement.item(i);

                if (category.getAttribute("maloai").equals(maloai)) {
                    Node parent = category.getParentNode();
                    parent.removeChild(category);
                    isFound = true;
                    break;
                }
            }

            if (isFound) {
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(db);
                transformer.transform(source, result);
                System.out.print("Remove successfully!");
            } else {
                System.out.print("Not found category ");
            }
            System.out.print("");
        } catch (Exception e) {
            e.getStackTrace();
        }
    }


}