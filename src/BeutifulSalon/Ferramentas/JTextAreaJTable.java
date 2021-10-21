/*
 * The MIT License
 *
 * Copyright 2021 Mateus.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package BeutifulSalon.Ferramentas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Mateus
 */
public class JTextAreaJTable extends JTextArea implements TableCellRenderer {
  
  
        public JTextAreaJTable() {  
            
            ManipulaFontes mf = new ManipulaFontes();
            setLineWrap(true);  
            setWrapStyleWord(true);  
            setFont(mf.getFont(mf.SEMIBOLD, Font.PLAIN, 15f)); // NOI18N  
            setMargin(new java.awt.Insets(0, 10, 0, 0)); 
            setBackground(Color.WHITE);
   
        }  
  
        @Override  
        public Component getTableCellRendererComponent(  
                JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {  
  
            
            this.setText(value.toString());  
            
            setText((value == null) ? "" : value.toString());  
            setSize(table.getColumnModel().getColumn(column).getWidth(),  
                    getPreferredSize().height);  
  
            if (table.getRowHeight(row) < getPreferredSize().height) {  
                table.setRowHeight(row, getPreferredSize().height );  
            }
  
            return this;  
        }  
    

}
