package controllers;

import models.*;
import views.*;

import java.util.List;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

public class ContactController {
    private final ContactView contactsView;
    private final int currentUser;
    private int selectionIndex;

    public ContactController(ContactView cv, int userid) {
        contactsView = cv;
        currentUser = userid;
        updateContactList();

        // assign action listeners
        contactsView.setAddButtonListener(new AddButtonListener());
        contactsView.setUpdateButtonListener(new UpdateButtonListener());
        contactsView.setDeleteButtonListener(new DeleteButtonListener());
        contactsView.setSearchButtonListener(new SearchButtonListener());
        contactsView.setExportButtonListener(new ExportButtonListener());
        contactsView.setListSelectionListener(new ContactListListener());
        contactsView.setSearchFieldListener(new SearchFieldListener());

        contactsView.setVisible(true);
    }

    // provide ContactsView with users contact list
    public void updateContactList() {
        DBManager data = new DBManager(currentUser);
        List<Contact> contacts = data.getContacts();
        contactsView.setContactsToModel(contacts);
    }

    // EVENT HANDLERS

    // adds a new contact to users contact list
    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DBManager data = new DBManager(currentUser);
            Contact contact = new Contact(
                    contactsView.getFirstName(),
                    contactsView.getLastName(),
                    contactsView.getPhoneNumber(),
                    contactsView.getEmail(),
                    currentUser
            );
            if (data.addContact(contact)) {
                JOptionPane.showMessageDialog(null, "Contact added successfully");
                updateContactList();
            }
            else
                JOptionPane.showMessageDialog(null, "Unexpected Error.");
        }
    }

    // updates a contacts information in users contact list
    private class UpdateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DBManager data = new DBManager(currentUser);
            List<Contact> contacts = contactsView.getContactList();
            Contact contact = contacts.get(selectionIndex);
            contact.setFirstName(contactsView.getFirstName());
            contact.setLastName(contactsView.getLastName());
            contact.setNumber(contactsView.getPhoneNumber());
            contact.setEmail(contactsView.getEmail());

            if (data.updateContact(contact)) {
                JOptionPane.showMessageDialog(null, "Contact information updated successfully");
                updateContactList();
            }
            else JOptionPane.showMessageDialog(null, "Contact update failed.");
        }
    }

    // deletes contact from users contact list
    private class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DBManager data = new DBManager(currentUser);
            List<Contact> contacts = contactsView.getContactList();

            if (data.removeContact(contacts.get(selectionIndex))) {
                JOptionPane.showMessageDialog(null, "Contact was deleted");
                updateContactList();
            }
            else JOptionPane.showMessageDialog(null, "Contact deletion failed.");
        }
    }

    // select contact in JList if search contains a contacts first or last name
    private class SearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Contact> contacts = contactsView.getContactList();
            String searchInput = contactsView.getSearch();
            int i = 0;
            for (Contact c : contacts) {
                if (searchInput.contains(c.getFirstName()) || searchInput.contains(c.getLastName())) {
                    contactsView.setSelection(i);
                    break;
                }
                i++;
            }
        }
    }

    // exports users contact list to .csv file
    private class ExportButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DBManager data = new DBManager(currentUser);
            if (data.exportCSV()) {
                JOptionPane.showMessageDialog(null, "Contact list exported to contacts.csv");
            }
            else JOptionPane.showMessageDialog(null, "Export contacts failed.");
        }
    }

    // clears text from search text field when clicked
    private class SearchFieldListener implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) { contactsView.clearSearchField(); }
        public void focusLost(FocusEvent e) { }
    }

    // populate respective text field with selected contact information
    private class ContactListListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent selection) {
            selectionIndex = contactsView.getSelection();
            List<Contact> contacts = contactsView.getContactList();

            // safety check for null selection
            if (selectionIndex >= 0) {
                contactsView.setFirstNameField(contacts.get(selectionIndex).getFirstName());
                contactsView.setLastNameField(contacts.get(selectionIndex).getLastName());
                contactsView.setPhoneField(contacts.get(selectionIndex).getNumber());
                contactsView.setEmailField(contacts.get(selectionIndex).getEmail());
            }
        }
    }
}
