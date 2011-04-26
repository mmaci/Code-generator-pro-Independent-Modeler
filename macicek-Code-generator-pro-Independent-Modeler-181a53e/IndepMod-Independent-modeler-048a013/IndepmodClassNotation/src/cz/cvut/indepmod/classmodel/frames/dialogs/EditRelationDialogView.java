package cz.cvut.indepmod.classmodel.frames.dialogs;

import cz.cvut.indepmod.classmodel.resources.Resources;
import java.awt.Frame;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 * Date: 21.11.2010
 * Time: 17:44:45
 * @author Lucky
 */
public class EditRelationDialogView extends AbstractClassModelDialog {
    
    public static final String TITLE = Resources.getString("dialog_edit_relation_title");

    public static final String SOURCE_CARD_LABEL_TEXT = Resources.getString("dialog_edit_relation_source_card");
    public static final String TARGET_CARD_LABEL_TEXT = Resources.getString("dialog_edit_relation_target_card");
    public static final String SAVE_TEXT = Resources.getString("dialog_edit_relation_save");
    public static final String CANCEL_TEXT = Resources.getString("dialog_edit_relation_cancel");

    protected JLabel sourceCardinLab;
    protected JLabel targetCardinLab;
    protected JComboBox sourceCardinality;
    protected JComboBox targetCardinality;
    protected JButton saveButton;
    protected JButton cancelButton;

    public EditRelationDialogView(Frame owner) {
        super(owner, TITLE);

        this.initLayout();
    }

    private void initLayout() {
        this.setLayout(new GridLayout(3, 2));

        this.sourceCardinLab = new JLabel(SOURCE_CARD_LABEL_TEXT);
        this.targetCardinLab = new JLabel(TARGET_CARD_LABEL_TEXT);
        this.sourceCardinality = new JComboBox();
        this.targetCardinality = new JComboBox();
        this.saveButton = new JButton(SAVE_TEXT);
        this.cancelButton = new JButton(CANCEL_TEXT);

        this.add(this.sourceCardinLab);
        this.add(this.sourceCardinality);
        this.add(this.targetCardinLab);
        this.add(this.targetCardinality);
        this.add(this.saveButton);
        this.add(this.cancelButton);
    }



}
