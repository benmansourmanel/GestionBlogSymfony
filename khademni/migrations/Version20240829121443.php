<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20240829121443 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE post DROP FOREIGN KEY fk_userp');
        $this->addSql('CREATE TABLE comments (id INT AUTO_INCREMENT NOT NULL, id_post INT DEFAULT NULL, id_user VARCHAR(255) NOT NULL, content VARCHAR(255) NOT NULL, image_url VARCHAR(255) NOT NULL, INDEX IDX_5F9E962AD1AA708F (id_post), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE likes (id INT AUTO_INCREMENT NOT NULL, id_post INT DEFAULT NULL, id_user VARCHAR(255) NOT NULL, is_liked SMALLINT NOT NULL, INDEX IDX_49CA4E7DD1AA708F (id_post), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE comments ADD CONSTRAINT FK_5F9E962AD1AA708F FOREIGN KEY (id_post) REFERENCES post (id)');
        $this->addSql('ALTER TABLE likes ADD CONSTRAINT FK_49CA4E7DD1AA708F FOREIGN KEY (id_post) REFERENCES post (id)');
        $this->addSql('DROP TABLE utilisateur');
        $this->addSql('DROP INDEX fk_userp ON post');
        $this->addSql('ALTER TABLE post CHANGE Id_user id_user VARCHAR(255) NOT NULL');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE utilisateur (id INT AUTO_INCREMENT NOT NULL, cin INT NOT NULL, nom VARCHAR(60) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, prenom VARCHAR(80) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, mail VARCHAR(200) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, password VARCHAR(220) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, role VARCHAR(210) CHARACTER SET utf8mb4 NOT NULL COLLATE `utf8mb4_unicode_ci`, tel INT NOT NULL, PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB COMMENT = \'\' ');
        $this->addSql('ALTER TABLE comments DROP FOREIGN KEY FK_5F9E962AD1AA708F');
        $this->addSql('ALTER TABLE likes DROP FOREIGN KEY FK_49CA4E7DD1AA708F');
        $this->addSql('DROP TABLE comments');
        $this->addSql('DROP TABLE likes');
        $this->addSql('ALTER TABLE post CHANGE id_user Id_user INT DEFAULT NULL');
        $this->addSql('ALTER TABLE post ADD CONSTRAINT fk_userp FOREIGN KEY (Id_user) REFERENCES utilisateur (id) ON UPDATE CASCADE ON DELETE CASCADE');
        $this->addSql('CREATE INDEX fk_userp ON post (Id_user)');
    }
}
