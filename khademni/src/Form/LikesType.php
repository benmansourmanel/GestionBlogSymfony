<?php
namespace App\Form;

use App\Entity\Likes;
use App\Entity\Post;
use App\Entity\Utilisateur;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;

class LikesType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('post', EntityType::class, [
                'class' => Post::class,
                'choice_label' => 'id',
            ])
            ->add('utilisateur', EntityType::class, [
                'class' => Utilisateur::class,
                'choice_label' => 'id', // Display user names or any other suitable property
            ])
      
            ->add('IsLiked');
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Likes::class,
        ]);
    }
}
