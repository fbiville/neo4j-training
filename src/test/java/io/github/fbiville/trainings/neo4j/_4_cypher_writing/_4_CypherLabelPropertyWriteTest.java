package io.github.fbiville.trainings.neo4j._4_cypher_writing;

import io.github.fbiville.trainings.neo4j.internal.DoctorWhoGraph;
import io.github.fbiville.trainings.neo4j.internal.GraphTests;
import org.junit.Before;
import org.junit.Test;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class _4_CypherLabelPropertyWriteTest extends GraphTests {

    @Before
    public void prepare() {
        DoctorWhoGraph.create(graphDb);
    }

    @Test
    public void should_add_original_name_property_for_david_tennant_node() {
        try (Transaction transaction = graphDb.beginTx()) {
            String cql = null /*TODO: write Cypher query*/;
            fail("You should add the property to David Tennant node");

            graphDb.execute(cql);
            transaction.success();
        }

        try (Transaction ignored = graphDb.beginTx()) {
            Node node = graphDb.findNode(Label.label("Actor"), "actor", "David Tennant");
            assertThat(node.getProperty("original_name")).isEqualTo("David McDonald");
        }
    }

    @Test
    public void should_change_original_name_property_for_david_tennant_node_to_something_comical() {
        should_add_original_name_property_for_david_tennant_node();

        try (Transaction transaction = graphDb.beginTx()) {
            String cql = null /*TODO: write Cypher query*/;
            fail("You should change the property to David Tennant node");

            graphDb.execute(cql);
            transaction.success();
        }

        try (Transaction ignored = graphDb.beginTx()) {
            Node node = graphDb.findNode(Label.label("Actor"), "actor", "David Tennant");
            assertThat(node.getProperty("original_name")).isEqualTo("Ronald McDonald");
        }
    }

    @Test
    public void should_add_scottish_nationality_label_to_the_existing_david_tennant_node() {
        try (Transaction transaction = graphDb.beginTx()) {
            String cql = null /*TODO: write Cypher query*/;
            fail("You should add the label to David Tennant node");

            graphDb.execute(cql);
            transaction.success();
        }

        try (Transaction ignored = graphDb.beginTx()) {
            Node node = graphDb.findNode(Label.label("Actor"), "actor", "David Tennant");
            assertThat(node.getLabels()).extracting(Label::name).containsOnly("DoctorWho", "Actor", "Scottish");
        }
    }

    @Test
    public void should_add_actor_male_and_scottish_labels_to_the_existing_david_tennant_node() {
        try (Transaction transaction = graphDb.beginTx()) {
            String cql = null /*TODO: write Cypher query*/;
            fail("You should add the labels to David Tennant node");

            graphDb.execute(cql);
            transaction.success();
        }

        try (Transaction ignored = graphDb.beginTx()) {
            Node node = graphDb.findNode(Label.label("Actor"), "actor", "David Tennant");
            assertThat(node.getLabels()).extracting(Label::name).containsOnly("DoctorWho", "Actor", "Scottish", "Male");
        }
    }

    @Test
    public void should_visit_all_nodes_and_relationships() {
        try (Transaction transaction = graphDb.beginTx()) {
            String cql = null /*TODO: write Cypher query*/;
            fail("You should visit all nodes and relationships");

            graphDb.execute(cql);
            transaction.success();
        }

        try (Transaction ignored = graphDb.beginTx()) {
            graphDb.getAllNodes().stream()
                .forEach(n -> assertThat(n.getProperty("visited")).isEqualTo(true));
            graphDb.getAllRelationships().stream()
                .forEach(n -> assertThat(n.getProperty("visited")).isEqualTo(true));
        }
    }

    @Test
    public void should_remove_salary_data_from_doctor_actors() {
        try (Transaction transaction = graphDb.beginTx()) {
            String cql = null /*TODO: write Cypher query*/;
            fail("You should remove the salaries of Doctor Who actors");
            
            graphDb.execute(cql);
            transaction.success();
        }

        try (Transaction ignored = graphDb.beginTx()) {
            ResourceIterator<Long> result = graphDb.execute("MATCH (doctor:Character {character: 'Doctor'})<-[:PLAYED]-(actor:Character) " +
                "WHERE EXISTS(actor.salary) RETURN COUNT(actor) AS count").columnAs("count");
            assertThat(result).containsOnly(0L);
        }
    }

    @Test
    public void should_add_story_arc_on_relationships_between_episodes() {
        try (Transaction transaction = graphDb.beginTx()) {
            String cql = null /*TODO: write Cypher query*/;
            fail("You should add the story arc between 'The Ribos Operation' and 'The Armageddon Factor' episodes");

            graphDb.execute(cql);
            transaction.success();
        }

        try (Transaction ignored = graphDb.beginTx()) {
            String cql =
                "MATCH p=(startEp:Episode {title: 'The Ribos Operation'})-[:NEXT*]->(endEp:Episode {title: 'The Armageddon Factor'}) " +
                "WITH relationships(p) AS rels " +
                "RETURN LENGTH(EXTRACT(x IN rels | x.story_arc)) AS count";

            ResourceIterator<Long> result = graphDb.execute(cql).columnAs("count");
            assertThat(result).containsOnly(5L);
        }
    }
}
