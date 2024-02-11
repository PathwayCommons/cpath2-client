package cpath.client;

import cpath.client.query.*;
import cpath.client.util.CPathException;
import org.biopax.paxtools.io.SimpleIOHandler;
import org.biopax.paxtools.model.Model;
import org.biopax.paxtools.model.level3.Pathway;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * INFO: when "cPath2Url" Java property is not set (e.g., -DcPath2Url="http://localhost:8080/"),
 * the default cpath2 endpoint URL is {@link CPathClient#DEFAULT_ENDPOINT_URL}.
 * Run with JVM opts: --add-opens=java.base/java.lang=ALL-UNNAMED --add-opens=java.base/java.lang.reflect=ALL-UNNAMED
 */
@Disabled //these tests depend on the data, thus disabled by default (not for daily builds)
public class CPathClientIT {

  Logger log = LoggerFactory.getLogger(CPathClientIT.class);

  static CPathClient client;

  static {
    client = CPathClient.newInstance(null); //use the URL from -DcPath2Url=<url> JVM option or the default PC2 URL
    client.setName("CPathClientIT");
  }

  final String testBioSourceUri = "http://identifiers.org/taxonomy/9606"; //TODO: will be bioregistry.io based URI soon...
  final String testPathwayUri = "http://identifiers.org/reactome/R-HSA-201451";


  @Test
  public final void testBiopaxMediaType() {
    String biopaxContentType = "application/vnd.biopax.rdf+xml";
    MediaType mediaType = MediaType.parseMediaType(biopaxContentType);
    assertNotNull(mediaType);
    assertEquals(biopaxContentType, mediaType.toString());
  }

  @Test
  public final void testConnectionEtc() throws CPathException {
    String endPointURL = client.getEndPointURL();
    log.info("cpath2 instance: " + endPointURL);
    String res = client.post("help/types", null, String.class);
    assertTrue(res.contains("BioSource"));
    res = client.get("help/types", null, String.class);
    assertTrue(res.contains("BioSource"));
  }

  @Test
  public final void testGetTopPathways() throws CPathException {
    SearchResponse result = client.createTopPathwaysQuery()
      .queryString("*").datasourceFilter(new String[]{"reactome"}).result();
    assertNotNull(result);
    assertFalse(result.getSearchHit().isEmpty());

    result = null;
    try {
      result = client.createTopPathwaysQuery().datasourceFilter(new String[]{"foo"}).result();
    } catch (CPathException e) {}
    // since pc9 beta; it sends "empty" data result...
    assertTrue(result == null || result.isEmpty());
  }

  @Test
  public final void testTraverse() {
    // must get a result w/o problems
    TraverseResponse resp = null;
    try {
      resp = client.createTraverseQuery()
        .propertyPath("Named/name")
        .sources(new String[]{testBioSourceUri})
        .result();
    } catch (CPathException e) {
      fail(e.toString());
    }
    assertNotNull(resp);
    assertFalse(resp.getTraverseEntry().isEmpty());
    TraverseEntry entry = resp.getTraverseEntry().get(0);
    // check value(s)
    assertFalse(entry.getValue().isEmpty());
    assertTrue(entry.getValue().contains("Homo sapiens")); //case matters!

    // non-exisitng uri in not error, but the corresp. list of values must be empty
    resp = null;
    try {
      resp = client.createTraverseQuery()
        .propertyPath("Named/name")
        .sources(new String[]{"bla-bla"})
        .result();
      //it does not anymore send error response; it returns "empty" result
      // fail("must throw CPathException now, for all error responses: 460, 452, 500...");
    } catch (CPathException e) {
    }
    //assertNull(resp); //empty response
    assertTrue(resp.isEmpty());

    //intentionally wrong path -> failure (error 452)
    try {
      resp = client.createTraverseQuery()
        .propertyPath("BioSource/participant")
        .sources(new String[]{testBioSourceUri})
        .result();
      fail("must throw CPathException and not something else");
    } catch (CPathException e) {
    } //ok to ignore

    //test with a reactome pathway URI that contains '#' (a POST should work but GET - fail)
    try {
      resp = client.createTraverseQuery()
        .propertyPath("Pathway/pathwayComponent")
        .sources(new String[]{testPathwayUri})
        .result();
    } catch (CPathException e) {
      fail(e.toString());
    }
    assertNotNull(resp);
    assertFalse(resp.isEmpty());
  }

  @Test
  public final void testSearch() {
    ServiceResponse resp = null;
    try {
      resp = client.createSearchQuery()
        .typeFilter(Pathway.class)
        .queryString("name:\"Signaling by BMP\"")
        .result();
    } catch (CPathException e) {
      fail(e.toString());
    }

    assertTrue(resp instanceof SearchResponse);
    assertFalse(((SearchResponse) resp).getSearchHit().isEmpty());
    assertEquals(Integer.valueOf(0), ((SearchResponse) resp).getPageNo());

    resp = null;
    try {
      resp = client.createSearchQuery()
        .typeFilter("Provenance")
        .allPages()
        .result();
    } catch (CPathException e) {
      fail(e.toString());
    }

    assertTrue(resp instanceof SearchResponse);
    assertFalse(((SearchResponse) resp).getSearchHit().isEmpty());
  }

  @Test //this test is even more dependent on the data there
  public final void testGetByUri() throws CPathException {
    String id = "BRCA2";
    Model m = client.createGetQuery()
      .sources(new String[]{id})
      .result();
    assertFalse(m == null);
    assertFalse(m.getObjects().isEmpty());

    String res = client.createGetQuery()
      .sources(new String[]{"JUN", "PTEN"})
      .stringResult(null); //biopax
    assertNotNull(res);
    assertTrue(res.contains("biopax"));

    res = client.createGetQuery()
      .sources(new String[]{"JUN", "PTEN"})
      .stringResult(OutputFormat.SBGN);
    assertNotNull(res);
//    log.info(res);
    assertTrue(res.contains("<sbgn"));
  }

  //@Ignore
  @Test
  public final void testPathsBetweenQuery() throws CPathException {
    Model model = client.createGraphQuery()
      .kind(GraphType.PATHSBETWEEN)
      .sources(new String[]{"JUN", "PTEN"})
      .limit(1)
      .result();

    System.out.println("model.getObjects(.size()) = " + model.getObjects().size());
    SimpleIOHandler h = new SimpleIOHandler();

    try {
      h.convertToOWL(model, new FileOutputStream("target/testPathsBetweenQuery.out.owl"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  @Test //this test id data-dependent
  public final void testGetPathwayByUri() throws CPathException {
    Model m = client.createGetQuery()
      .sources(new String[]{testPathwayUri})
      .result();
    assertNotNull(m);
    assertFalse(m.getObjects(Pathway.class).isEmpty());
  }
}
