package dbconfigutil;

import exceptions.TagAttributeNotFoundException;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author len
 */
public class ParseSqlConfig {

    private static Document parse(URL url) throws DocumentException {
        SAXReader reader = new SAXReader();
        return reader.read(url);
    }

    private static void parseArguments(Element arguments, ArrayList<SqlField> inputs) throws TagAttributeNotFoundException{
        Attribute attribute = null;
        SqlField sf = null;;
        for (Iterator<Element> argIt = arguments.elementIterator(); argIt.hasNext();) {
            // argument
            Element arg = argIt.next();
            sf = new SqlField();

            sf.setDbName(arg.getStringValue());
            attribute = arg.attribute("type");
            if (attribute == null) {
                throw new TagAttributeNotFoundException(arg.getName() + " -> type");
            }
            sf.setType(attribute.getValue());

            attribute = arg.attribute("seq");
            if (attribute == null) {
                throw new TagAttributeNotFoundException(arg.getName() + " -> seq");
            }
            sf.setSeq(Integer.valueOf(attribute.getValue()));

            inputs.add(sf);
        }
    }

    private static void parseResults(Element results, ArrayList<SqlField> outputs) throws TagAttributeNotFoundException{
        Attribute attribute = null;
        SqlField sf = null;;
        for (Iterator<Element> resultsIt = results.elementIterator(); resultsIt.hasNext();) {
            // rs
            Element rs = resultsIt.next();
            sf = new SqlField();

            sf.setDbName(rs.getStringValue());
            attribute = rs.attribute("var_name");
            if (attribute == null) {
                throw new TagAttributeNotFoundException(rs.getName() + " -> var_name");
            }
            sf.setVarName(attribute.getValue());

            attribute = rs.attribute("type");
            if (attribute == null) {
                throw new TagAttributeNotFoundException(rs.getName() + " -> type");
            }
            sf.setType(attribute.getValue());

            outputs.add(sf);
        }
    }

    public static ConcurrentHashMap<String, SqlStruct> parseConfig(URL url) throws DocumentException, TagAttributeNotFoundException {
        Document d = parse(url);
        Element root = d.getRootElement();

        ConcurrentHashMap<String, SqlStruct> sqlMap = new ConcurrentHashMap<>(32);
        SqlStruct ss;

        String name;
        for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
            // items
            Element items = it.next();

            for (Iterator<Element> itemsIt = items.elementIterator(); itemsIt.hasNext();) {
                // item
                Element item = itemsIt.next();
                ss = new SqlStruct();
                for (Iterator<Element> itemIt = item.elementIterator(); itemIt.hasNext();) {
                    // item in item: name/sql/arguments/results
                    Element itemItem = itemIt.next();
                    name = itemItem.getName();
                    if ("name".equals(name)) {
                        ss.setName(itemItem.getStringValue());
                    }
                    if ("sql".equals(name)) {
                        ss.setSql(itemItem.getStringValue());
                    }
                    // arguments
                    if ("arguments".equals(name)) {
                        parseArguments(itemItem, ss.inputs);
                    }

                    // results
                    if ("results".equals(name)) {
                        parseResults(itemItem, ss.outputs);
                    }
                }
                sqlMap.put(ss.getName(), ss);
            }
        }

        return sqlMap;
    }
}
