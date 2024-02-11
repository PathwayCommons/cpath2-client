package cpath.client.query;

import org.apache.commons.text.StringEscapeUtils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Search hit java bean.
 * 
 * @author rodche
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "SearchHit",
        propOrder = {
                "uri",
                "biopaxClass",
                "name",
                "dataSource",
                "organism",
                "pathway",
                "excerpt",
                "numParticipants",
                "numProcesses",
                "size"
        }
)
public class SearchHit implements Serializable {
    @XmlElement(required = true)
    private String uri;
    @XmlElement(required = true)
    private String biopaxClass;
    private String name;
    private List<String> dataSource;
    private List<String> organism;
    private List<String> pathway;
    private String excerpt;
    private Integer size;
    private Integer numParticipants;
    private Integer numProcesses;

    public SearchHit() {
	}

    public String getUri() {
        return uri;
    }
    public void setUri(String value) {
        this.uri = value;
    }
	
	public String getName() {
        return this.name;
    }
	public void setName(String name) {
		this.name = name;
	}

    /**
     * The BioPAX type (short name),
     * e.g., "ProteinReference". 
     * @return
     */
    public String getBiopaxClass() {
        return biopaxClass;
    }

    public void setBiopaxClass(String value) {
        this.biopaxClass = value;
    }

    /**
     * The list of data source (Provenance) URIs. 
     * @return
     */
    public List<String> getDataSource() {
        if (dataSource == null) {
            dataSource = new ArrayList<>();
        }
        return this.dataSource;
    }
    public void setDataSource(List<String> dataSource) {
		this.dataSource = dataSource;
	}

    /**
     * The list of organism (BioSource) URIs. 
     * @return
     */
    public List<String> getOrganism() {
        if (organism == null) {
            organism = new ArrayList<>();
        }
        return this.organism;
    }
    public void setOrganism(List<String> organism) {
		this.organism = organism;
	}
   
    /**
     * The list of parent pathwaya' URIs. 
     * @return
     */
    public List<String> getPathway() {
        if (pathway == null) {
            pathway = new ArrayList<>();
        }
        return this.pathway;
    }
    public void setPathway(List<String> pathway) {
		this.pathway = pathway;
	}

    
    public String getExcerpt() {
        return excerpt;
    }
    public void setExcerpt(String value) {
        this.excerpt = value;
    }

    /**
     * For a BioPAX Interaction, Pathway, or Complex,
     * this is a number of physical entity or gene participants or components.
     *
     * @return
     */
    public Integer getNumParticipants() {
        return numParticipants;
    }
    public void setNumParticipants(Integer value) {
        this.numParticipants = value;
    }

    /**
     * For a BioPAX process (Interaction or Pathway),
     * this is a number of sub-processes.
     *
     * @return
     */
    public Integer getNumProcesses() {
        return numProcesses;
    }
    public void setNumProcesses(Integer value) {
        this.numProcesses = value;
    }

    @Override
    public String toString() {
    	return (name != null) ? StringEscapeUtils.unescapeHtml4(name) : uri;
    }
}
