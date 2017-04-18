/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mydictionary.gui;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import mydictionary.bussiness.DateHandler;
import mydictionary.bussiness.FavoriteWordHandler;
import mydictionary.data.IniFileHelper;
import mydictionary.data.XmlDataHelper;
import mydictionary.dto.Dictionary;
import mydictionary.dto.Dictionary.DictionaryType;
import mydictionary.dto.LookedupDateList;
import java.util.Date;
import java.util.HashMap;
import javax.swing.DefaultListModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import mydictionary.bussiness.StatitisticHandler;

/*
 *
 * @author NghiaTruongNgoc
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnDelete;
    private javax.swing.JButton jBtnFav;
    private javax.swing.JButton jBtnSearch;
    private javax.swing.JButton jBtnStatistic;
    private javax.swing.JButton jBtnSwitch;
    private javax.swing.JList<String> jFavList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTblStat;
    private javax.swing.JTextArea jTxaInfor;
    private javax.swing.JTextField jTxfWord;
    private javax.swing.JList<String> jWordsList;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker2;
    // End of variables declaration//GEN-END:variables

    DictionaryType type;
    private final Dictionary dicEV;
    private final Dictionary dicVE;
    private final Dictionary favWordsEV;
    private final Dictionary favWordsVE;

    private final LookedupDateList lookedupDateListEV;
    private final LookedupDateList lookedupDateListVE;

    DefaultListModel<String> listEV;
    DefaultListModel<String> listVE;

    boolean searchPressed = false;

    private String meaning;
    private String word;
    private Date currentDate;
    
    
    public MainFrame() {
        initComponents();
        
        DefaultTableModel model = new DefaultTableModel(); 
        model.addColumn("Từ");
        model.addColumn("Số lần tra");
        jTblStat.setModel(model);
        
        listEV = new DefaultListModel<>();
        listVE = new DefaultListModel<>();
        
        lookedupDateListEV = IniFileHelper.
                getLookedupDateList(IniFileHelper.INI_PATH_EV);
        lookedupDateListVE = IniFileHelper.
                getLookedupDateList(IniFileHelper.INI_PATH_VE);

        currentDate = DateHandler.getCurrentDate();

        DateHandler.formatDatePicker(jXDatePicker1);
        DateHandler.formatDatePicker(jXDatePicker2);
        getRootPane().setDefaultButton(jBtnSearch);
        FavoriteWordHandler.clearWords(jFavList);
        FavoriteWordHandler.clearWords(jWordsList);

        type = DictionaryType.EV;
        dicEV = XmlDataHelper.loadResources(XmlDataHelper.PATH_EV, listEV);
        dicVE = XmlDataHelper.loadResources(XmlDataHelper.PATH_VE, listVE);

        favWordsEV = XmlDataHelper.loadFavorites(XmlDataHelper.PATH_FAV_EV);
        favWordsVE = XmlDataHelper.loadFavorites(XmlDataHelper.PATH_FAV_VE);

        FavoriteWordHandler.loadWordstoList(listEV, jWordsList);

        if (favWordsEV != null) {
            FavoriteWordHandler.loadWordstoList(favWordsEV, jFavList);
        }

        jBtnFav.setEnabled(false);

        jFavList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent ev) {
                String word = jFavList.getSelectedValue();
                String meaning;
                if (type == DictionaryType.EV) {
                    meaning = favWordsEV.getValue(word);
                } else {
                    meaning = favWordsVE.getValue(word);
                }

                jBtnFav.setEnabled(false);
                jTxaInfor.setText(meaning);
                jTxaInfor.setCaretPosition(0);
            }
        });

        jWordsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent ev) {
                if (!ev.getValueIsAdjusting() && !searchPressed) {
                    word = jWordsList.getSelectedValue();
                    if (type == DictionaryType.EV) {
                        meaning = dicEV.getValue(word);
                    } else {
                        meaning = dicVE.getValue(word);
                    }

                    if (type == DictionaryType.EV) {
                        if (word != null) {
                            lookedupDateListEV.increaseFreqbyOne(currentDate, word);
                        }
                    } else if (word != null) {
                        lookedupDateListVE.increaseFreqbyOne(currentDate, word);
                    }

                    jBtnFav.setEnabled(true);
                    jTxfWord.setText(word);
                    jTxaInfor.setText(meaning);
                    jTxaInfor.setCaretPosition(0);
                }
            }
        });

        if (dicEV == null) {
            jTxaInfor.setText("Could not find file Anh_Viet.xml,"
                    + " please contact 1412346 for more!");
        }

        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTxfWord = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTxaInfor = new javax.swing.JTextArea();
        jBtnSearch = new javax.swing.JButton();
        jBtnSwitch = new javax.swing.JButton();
        jBtnFav = new javax.swing.JButton();
        jBtnStatistic = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jFavList = new javax.swing.JList<>();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        jXDatePicker2 = new org.jdesktop.swingx.JXDatePicker();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jWordsList = new javax.swing.JList<>();
        jBtnDelete = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTblStat = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("My Dictionary");
        setLocation(new java.awt.Point(200, 200));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTxaInfor.setEditable(false);
        jTxaInfor.setColumns(20);
        jTxaInfor.setRows(5);
        jScrollPane1.setViewportView(jTxaInfor);

        jBtnSearch.setText("Tra từ");
        jBtnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSearchActionPerformed(evt);
            }
        });

        jBtnSwitch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mydictionary/image/switch.png"))); // NOI18N
        jBtnSwitch.setText("Chuyển Việt/Anh");
        jBtnSwitch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSwitchActionPerformed(evt);
            }
        });

        jBtnFav.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mydictionary/image/fav.png"))); // NOI18N
        jBtnFav.setText("Từ yêu thích");
        jBtnFav.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnFavActionPerformed(evt);
            }
        });

        jBtnStatistic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mydictionary/image/stat.png"))); // NOI18N
        jBtnStatistic.setText("Thống kê");
        jBtnStatistic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnStatisticActionPerformed(evt);
            }
        });

        jFavList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jFavList);

        jLabel1.setText("Từ");

        jLabel3.setText("Đến");

        jWordsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jWordsList.setAutoscrolls(false);
        jWordsList.setVisibleRowCount(100000);
        jScrollPane3.setViewportView(jWordsList);

        jBtnDelete.setLabel("Xóa yêu thích");
        jBtnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnDeleteActionPerformed(evt);
            }
        });

        jTblStat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTblStat);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                    .addComponent(jTxfWord))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 674, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jXDatePicker1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jXDatePicker2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnStatistic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnFav, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                    .addComponent(jBtnSwitch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jBtnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBtnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnSwitch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jBtnFav, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jXDatePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnStatistic, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTxfWord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jBtnSearch.getAccessibleContext().setAccessibleName("jBtnSearch");
        jBtnDelete.getAccessibleContext().setAccessibleName("jBtnDelete");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        XmlDataHelper.saveFavorites(favWordsEV, DictionaryType.EV);
        XmlDataHelper.saveFavorites(favWordsVE, DictionaryType.VE);

        IniFileHelper.saveLookedupDateList(lookedupDateListEV, IniFileHelper.INI_PATH_EV);
        IniFileHelper.saveLookedupDateList(lookedupDateListVE, IniFileHelper.INI_PATH_VE);
    }//GEN-LAST:event_formWindowClosing

    private void jBtnFavActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnFavActionPerformed
        if (type == DictionaryType.EV) {
            favWordsEV.addRecord(word, meaning);
        } else {
            favWordsVE.addRecord(word, meaning);
        }
        FavoriteWordHandler.addWordtoList(word, jFavList);
        jBtnFav.setEnabled(false);
        JOptionPane.showMessageDialog(this, "Đã lưu " + word + " vào yêu thích!");
    }//GEN-LAST:event_jBtnFavActionPerformed

    private void jBtnSwitchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSwitchActionPerformed
        
        jTxaInfor.setText("");
        //FavoriteWordHandler.clearWords(jStatList);
        DefaultTableModel model = (DefaultTableModel) jTblStat.getModel();
        model.setRowCount(0);
        FavoriteWordHandler.clearWords(jFavList);
        jBtnFav.setEnabled(false);
        if (type == DictionaryType.EV) {
            jBtnSwitch.setText("Chuyển Anh/Việt");
            type = DictionaryType.VE;
            FavoriteWordHandler.loadWordstoList(listVE, jWordsList);
            jWordsList.ensureIndexIsVisible(0);
            
            if (favWordsVE != null) 
                FavoriteWordHandler.loadWordstoList(favWordsVE, jFavList);
            
        } else {
            type = DictionaryType.EV;
            jBtnSwitch.setText("Chuyển Việt/Anh");
            FavoriteWordHandler.loadWordstoList(listEV, jWordsList);
            jWordsList.ensureIndexIsVisible(0);
            
            if (favWordsEV != null) 
                FavoriteWordHandler.loadWordstoList(favWordsEV, jFavList);
        }
    }//GEN-LAST:event_jBtnSwitchActionPerformed

    private void jBtnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSearchActionPerformed

        searchPressed = true;
        if (type == DictionaryType.EV) {
            if (dicEV == null) {
                jTxaInfor.setText("Could not find file Anh_Viet.xml,"
                        + " please contact 1412346 for more!");
                return;
            }
            word = jTxfWord.getText();
            meaning = dicEV.getValue(word);

            if (null != meaning) {
                for (int i = 0; i < listEV.size(); i++) {
                    if (listEV.get(i).equals(word)) {
                        jWordsList.setSelectedIndex(i);
                    }
                }
                searchPressed = false;
                jWordsList.ensureIndexIsVisible(jWordsList.getSelectedIndex());
                if (null != word) {
                    lookedupDateListEV.increaseFreqbyOne(currentDate, word);
                }
            }
        } else {
            if (dicVE == null) {
                jTxaInfor.setText("Could not find file Viet_Anh.xml,"
                        + " please contact 1412346 for more!");
                return;
            }
            word = jTxfWord.getText();
            meaning = dicVE.getValue(word);

            if (null != meaning) {
                for (int i = 0; i < listVE.size(); i++) {
                    if (listVE.get(i).equals(word)) {
                        jWordsList.setSelectedIndex(i);
                    }
                }
                searchPressed = false;
                jWordsList.ensureIndexIsVisible(jWordsList.getSelectedIndex());
                if (word != null) {
                    lookedupDateListVE.increaseFreqbyOne(currentDate, word);
                }
            }
        }
        if (null == meaning) {
            jTxaInfor.setText("Word " + word + " not found..");
            jBtnFav.setEnabled(false);
        } else {
            jTxaInfor.setText(meaning);
            jTxaInfor.setCaretPosition(0);
            jBtnFav.setEnabled(true);
        }
    }//GEN-LAST:event_jBtnSearchActionPerformed

    private void jBtnStatisticActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnStatisticActionPerformed
        Date dateStart = jXDatePicker1.getDate();
        Date dateEnd = jXDatePicker2.getDate();

        if (dateEnd == null || dateStart == null) {
            return;
        }
        
        if (dateEnd.before(dateStart)) {
            JOptionPane.showMessageDialog(this, "Sai ngày trước sau!");
            return;
        }

        HashMap<String, String> statWord;
        StatitisticHandler.exceptionChecker = 0;
        if (type == DictionaryType.EV) {
            statWord = StatitisticHandler.parseInforDateList(lookedupDateListEV,
                    DateHandler.dateToString(dateStart),
                    DateHandler.dateToString(dateEnd));
        } else {
            statWord = StatitisticHandler.parseInforDateList(lookedupDateListVE,
                    DateHandler.dateToString(dateStart),
                    DateHandler.dateToString(dateEnd));
        }
        
        if (StatitisticHandler.exceptionChecker == 1)
        {
            JOptionPane.showMessageDialog(this, "Sai định dạng ngày!");
            return;
        }
        if (StatitisticHandler.exceptionChecker == 2)
        {
            JOptionPane.showMessageDialog(this, "Không tìm thấy từ nào được tra!");
            return;
        }
        
        jTblStat.setModel(StatitisticHandler.hashMaptoTable(statWord));
    }//GEN-LAST:event_jBtnStatisticActionPerformed

    private void jBtnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDeleteActionPerformed
        // TODO add your handling code here:
        String wordtoDelete = jFavList.getSelectedValue();
        int index = jFavList.getSelectedIndex();

        if (wordtoDelete == null) {
            JOptionPane.showMessageDialog(this, "Hãy chọn một từ để xóa");
            return;
        }
        if (type == DictionaryType.EV) {
            favWordsEV.removeWord(wordtoDelete);
        } else {
            favWordsVE.removeWord(wordtoDelete);
        }

        ((DefaultListModel) jFavList.getModel()).remove(index);
    }//GEN-LAST:event_jBtnDeleteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame mFrame = new MainFrame();
                mFrame.setVisible(true);

            }
        });
    }
}
