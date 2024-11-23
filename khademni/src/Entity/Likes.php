<?php
namespace App\Entity;

use App\Repository\LikesRepository;
use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;

#[ORM\Entity(repositoryClass: LikesRepository::class)]
class Likes
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\ManyToOne(targetEntity: Post::class)]
    #[ORM\JoinColumn(name: 'id_post', referencedColumnName: 'id')]
    private ?Post $post = null;

    #[ORM\ManyToOne(targetEntity: Utilisateur::class)]
    #[ORM\JoinColumn(name: "user_id", referencedColumnName: "id")]
    private ?Utilisateur $utilisateur = null;
    

    public function getUtilisateur(): ?Utilisateur
    {
        return $this->utilisateur;
    }

    public function setUtilisateur(?Utilisateur $utilisateur): self
    {
        $this->utilisateur = $utilisateur;

        return $this;
    }

    #[ORM\Column(type: Types::SMALLINT)]
    private ?int $IsLiked = null;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getPost(): ?Post
    {
        return $this->post;
    }

    public function setPost(?Post $post): static
    {
        $this->post = $post;

        return $this;
    }

  

    public function getIsLiked(): ?int
    {
        return $this->IsLiked;
    }

    public function setIsLiked(int $IsLiked): static
    {
        $this->IsLiked = $IsLiked;

        return $this;
    }
}
