package gen;

import java.util.Collection;
import java.util.logging.Logger;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.Utilities;
import org.openide.windows.Mode;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import cz.cvut.indepmod.classmodel.api.model.IClassModelModel;

public class ClassModel extends TopComponent {

    private static final String PREFERRED_ID ="ClassModel";
    private static final String DISPLAY_NAME = "Class Model";
    private static final Logger LOG = Logger.getLogger(ClassModel.class.getName());
    private static ClassModel instance;
    private IClassModelModel model;
    private Lookup.Result<IClassModelModel> modelLookup;
    private IClassModelModelLookupListener ClassModelLookupLsnr;


    private ClassModel() {
        this.model = null;
        this.ClassModelLookupLsnr = new IClassModelModelLookupListener();
        this.setDisplayName(DISPLAY_NAME);
    }

//    @Override
//    public List<Mode> availableModes(List<Mode> modes) {
//        for (Mode mode : modes) {
//            if (!mode.getName().equals("rightSlidingSide")) {
//                modes.remove(mode);
//            }
//        }
//        return modes;
//    }

//    public IClassModelModel getModel() {
//        return model;
//    }
//
//    public void setModel(IClassModelModel model) {
//        this.model = model;
//    }

    public static synchronized ClassModel getDefault() {
        if (instance == null) {
            instance = new ClassModel();
        }
        return instance;
    }

//    public static synchronized ClassModel findInstance() {
//        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
//        if (win == null) {
//            LOG.warning("Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
//            return getDefault();
//        }
//        if (win instanceof ClassModel) {
//            return (ClassModel) win;
//        }
//        LOG.warning(
//                "There seem to be multiple components with the '" + PREFERRED_ID
//                + "' ID. That is a potential source of errors and unexpected behavior.");
//        return getDefault();
//    }

    @Override
    public void open() {
        Mode m = WindowManager.getDefault().findMode("properties");
        if (m != null) {
            m.dockInto(this);
        }
        super.open();
    }

    @Override
    protected void componentClosed() {
        this.modelLookup.removeLookupListener(this.ClassModelLookupLsnr);
        this.modelLookup = null;
    }

    @Override
    protected void componentOpened() {
        this.modelLookup = Utilities.actionsGlobalContext().lookup(new Lookup.Template<IClassModelModel>(IClassModelModel.class));
        this.modelLookup.addLookupListener(this.ClassModelLookupLsnr);
    }

    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }

    private class IClassModelModelLookupListener implements LookupListener {

        @Override
        public void resultChanged(LookupEvent le) {
            Lookup.Result r = (Lookup.Result) le.getSource();
            Collection c = r.allInstances();
            if (c.isEmpty()) {
                LOG.fine("There is no model, i am not changing it.");
            } else {
                LOG.fine("There is a model, i am switching to it.");
                IClassModelModel toolModel = (IClassModelModel) c.iterator().next();
                model = toolModel;
            }
        }
    }
}
