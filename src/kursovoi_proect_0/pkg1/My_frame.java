/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kursovoi_proect_0.pkg1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class My_frame extends JFrame {
    
    private JButton button1, button2, button3;
    private JSlider size;
    private JMenuBar menuBar;
    private JLabel massiv, pust_massiv, sort, label_rand, sort_massiv, label_sort;
    int[] array;
    
    public My_frame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setSize(800, 600); 
        setLayout(null);
        setTitle("Пирамидальная сортировка");
        
        menuBar = new JMenuBar();
       	this.setJMenuBar(menuBar);
        JMenu editMenu = new JMenu("File");
        menuBar.add(editMenu);
        JMenuItem pasteItem = new JMenuItem("Загрузить из файла");
        editMenu.add(pasteItem);
        ActionListener listener = new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {//выбираем файл
            JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(null, "Открыть файл");                
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    try{//считываем файл построчно
                        FileInputStream fstream = new FileInputStream(file);
                        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
                        String strLine;
                        strLine = br.readLine();
                        String[] strarr = strLine.split(" ");
                        array = new int[strarr.length];
                        for (int i=0; i<strarr.length; i++){//переводим в int
                            array[i]=Integer.parseInt(strarr[i]);
                        }
                        pust_massiv.setText(java.util.Arrays.toString(array));
                     }catch (Exception e){
                        System.out.println("Ошибка");
                        array = null;
                     }
                }
            }
        };
        pasteItem.addActionListener(listener);

        button1 = new JButton("Создать");
        button1.setBounds(300, 35, 90, 25);//подоборать местоположение и размер
        button1.addActionListener(new Button_manager());
        add(button1);
        
        button2 = new JButton("Sort up");
        button2.setBounds(155, 130, 80, 25);//подоборать местоположение и размер
        button2.addActionListener(new Button2_manager());
        add(button2);
        
        button3 = new JButton("Sort down");
        button3.setBounds(255, 130, 95, 25);//подоборать местоположение и размер
        button3.addActionListener(new Button3_manager());
        add(button3);
        
        massiv = new JLabel("Выберете размер массива:");
        massiv.setBounds(10, 0, 200, 100);//подоборать местоположение и размер
        add(massiv);
        
        size = new JSlider(JSlider.HORIZONTAL, 10, 20, 10);
        size.setBounds(180, 35, 100, 50);//подоборать местоположение и размер
        size.setMinorTickSpacing(1); 
        size.setMajorTickSpacing(5); 
        size.setPaintTicks(true); 
        size.setPaintLabels(true);
        add(size);
        
         label_rand = new JLabel("Полученный массив:");
        label_rand.setBounds(10, 45, 200, 100);//подоборать местоположение и размер
        add(label_rand);
        
        pust_massiv = new JLabel("");
        pust_massiv.setBounds(150, 45, 400, 100);//подоборать местоположение и размер
        add(pust_massiv);
        
         label_rand = new JLabel("Отсортировать массив");
        label_rand.setBounds(10, 95, 200, 100);//подоборать местоположение и размер
        add(label_rand);
        
        sort_massiv = new JLabel("");
        sort_massiv.setBounds(170, 125, 400, 100);//подоборать местоположение и размер
        add(sort_massiv);
        
         label_sort = new JLabel("Отсортированный массив:");
        label_sort.setBounds(10, 125, 200, 100);//подоборать местоположение и размер
        add(label_sort);
    }
    
    private class Button_manager implements ActionListener {
        public void actionPerformed(ActionEvent e){
            int value = size.getValue();
            array=new int[value];
            for(int i=0; i<value; i++){
                array[i] = (int) Math.round(Math.random() * 99);
                }	
            pust_massiv.setText(java.util.Arrays.toString(array));
        }         
    }
    
    private class Button2_manager implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try { 
                Sort.sort(array, true);
                } catch (Throwable t) {} 
            sort_massiv.setText(java.util.Arrays.toString(array));
        }         
    }
    
    private class Button3_manager implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try { 
                Sort.sort(array, false);
                } catch (Throwable t) {} 
            sort_massiv.setText(java.util.Arrays.toString(array));
        }         
    }
}
