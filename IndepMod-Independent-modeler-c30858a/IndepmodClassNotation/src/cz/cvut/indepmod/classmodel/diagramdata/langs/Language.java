package cz.cvut.indepmod.classmodel.diagramdata.langs;

import cz.cvut.indepmod.classmodel.api.model.IType;
import java.util.HashSet;
import java.util.Set;

/**
 * Date: 5.4.2011
 * Time: 13:22:43
 * @author Lucky
 *
 * This class represents the language and its default data type set
 */
public class Language {

    private String langName;

    private Set<IType> staticDataTypes;

    public Language() {
        this.langName = "";
        this.staticDataTypes = new HashSet<IType>();
    }

    public Language(String langName, Set<IType> staticDataTypes) {
        this.langName = langName;
        this.staticDataTypes = staticDataTypes;
    }

    public String getLangName() {
        return langName;
    }

    public void setLangName(String langName) {
        this.langName = langName;
    }

    public Set<IType> getStaticDataTypes() {
        return new HashSet<IType>(staticDataTypes);
    }

    public void setStaticDataTypes(Set<IType> staticDataTypes) {
        this.staticDataTypes = new HashSet<IType>(staticDataTypes);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Language other = (Language) obj;
        if ((this.langName == null) ? (other.langName != null) : !this.langName.equals(other.langName)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + (this.langName != null ? this.langName.hashCode() : 0);
        return hash;
    }

}
