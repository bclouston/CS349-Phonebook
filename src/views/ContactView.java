package views;

import models.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.*;

public class ContactView extends JFrame {
    private JPanel contactsPanel;
    private JTextField firstNameField, lastNameField, phoneField ,emailField, searchField;
    private JButton addContactButton, searchButton, exportButton, deleteButton, updateButton;
    private JList<String> contactsList;
    private List<Contact> contactList;
    private DefaultListModel<String> listModel;

    public ContactView() {
        setTitle("Phonebook Management: Contacts");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(contactsPanel);
        setSize(700, 500);
        listModel = new DefaultListModel<>();
    }

    // event listener setters

    public void setAddButtonListener(ActionListener listener) {
        addContactButton.addActionListener(listener);
    }

    public void setDeleteButtonListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    public void setUpdateButtonListener(ActionListener listener) {
        updateButton.addActionListener(listener);
    }

    public void setSearchButtonListener(ActionListener listener) {
        searchButton.addActionListener(listener);
    }

    public void setExportButtonListener(ActionListener listener) {
        exportButton.addActionListener(listener);
    }

    public void setListSelectionListener(ListSelectionListener listener) {
        contactsList.addListSelectionListener(listener);
    }

    public void setSearchFieldListener(FocusListener listener) {
        searchField.addFocusListener(listener);
    }

    // setters

    public void setFirstNameField(String first) {
        firstNameField.setText(first);
    }

    public void setLastNameField(String last) {
        lastNameField.setText(last);
    }

    public void setPhoneField(String phone) {
        phoneField.setText(phone);
    }

    public void setEmailField(String email) {
        emailField.setText(email);
    }

    public void setSelection(int i) {
        contactsList.setSelectedIndex(i);
    }

    public void clearSearchField() {
        searchField.setText("");
    }

    public void setContactsToModel(List<Contact> contacts) {
        contactList = contacts;
        listModel.clear();
        for (Contact c : contacts)
            listModel.addElement(c.getFirstName() + " " + c.getLastName() + " : " + c.getNumber());
        contactsList.setModel(listModel);
    }

    // getters

    public String getFirstName() {
        return firstNameField.getText();
    }

    public String getLastName() {
        return lastNameField.getText();
    }

    public String getPhoneNumber() {
        return phoneField.getText();
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getSearch() {
        return searchField.getText();
    }

    public int getSelection() {
        return contactsList.getSelectedIndex();
    }

    public List<Contact> getContactList() {
        return contactList;
    }

}
