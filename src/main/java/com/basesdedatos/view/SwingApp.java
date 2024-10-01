package com.basesdedatos.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.List;
import java.util.jar.JarEntry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.basesdedatos.model.Actor;
import com.basesdedatos.repository.ActorRepository;
import com.basesdedatos.repository.Repository;
import com.mysql.cj.xdevapi.JsonArray;

public class SwingApp extends JFrame{
    private final Repository<Actor> ActorRepository;
    private final JTable ActorTable;

    public SwingApp(){
        setTitle("Gestion Sakila");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,300);

        ActorTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(ActorTable);
        add(scrollPane,BorderLayout.CENTER);

        JButton addButton = new JButton("Agregar");
        JButton updateButton = new JButton("Actualizar");
        JButton deleteButton = new JButton("Borrar");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel,BorderLayout.SOUTH);

        ActorRepository = new ActorRepository();

        listActors();

    }
    
    private void listActors() {
        try {
            List<Actor> actors = ActorRepository.findAll();

            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("ID");
            tableModel.addColumn("Primer Nombre");

            for (Actor actor : actors) {
                Object[] dataRow = {
                    actor.getActor_id(),
                    actor.getFirst_name()
                };
                tableModel.addRow(dataRow);
            }
            ActorTable.setModel(tableModel);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,"Error getAll","Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

