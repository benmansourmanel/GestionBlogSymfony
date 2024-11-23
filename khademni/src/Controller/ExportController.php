<?php

namespace App\Controller;

use App\Entity\Post;
use Doctrine\ORM\EntityManagerInterface;
use PhpOffice\PhpSpreadsheet\Spreadsheet;
use PhpOffice\PhpSpreadsheet\Writer\Xlsx;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ExportController extends AbstractController
{
    /**
     * @Route("/export/excel", name="export_excel")
     */
    public function exportExcel(EntityManagerInterface $entityManager): Response
    {
        // Fetch all Post entities
        $repository = $entityManager->getRepository(Post::class);
        $data = $repository->findAll();
    
        // Create a new Spreadsheet object
        $spreadsheet = new Spreadsheet();
        $sheet = $spreadsheet->getActiveSheet();
    
        // Set header titles
        $sheet->setCellValue('A1', 'ID')
              ->setCellValue('B1', 'Title')
              ->setCellValue('C1', 'Content')
              ->setCellValue('D1', 'Image')
              ->setCellValue('E1', 'Created At')
              ->setCellValue('F1', 'Updated At');
    
        // Add data rows
        $rowIndex = 2;
        foreach ($data as $item) {
            $sheet->setCellValue('A' . $rowIndex, $item->getId())
                  ->setCellValue('B' . $rowIndex, $item->getTitle())
                  ->setCellValue('C' . $rowIndex, $item->getContent())
                  ->setCellValue('D' . $rowIndex, $item->getImage())
                  ->setCellValue('E' . $rowIndex, $item->getCreatedAt()->format('Y-m-d H:i:s'))
                  ->setCellValue('F' . $rowIndex, $item->getUpdateAt() ? $item->getUpdateAt()->format('Y-m-d H:i:s') : '');
    
            $rowIndex++;
        }
    
        // Write the spreadsheet to a temporary file
        $writer = new Xlsx($spreadsheet);
        $tempFile = tempnam(sys_get_temp_dir(), 'excel');
        $writer->save($tempFile);
    
        // Create a response with the Excel file content
        $response = new Response(file_get_contents($tempFile));
        $response->headers->set('Content-Type', 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet');
        $response->headers->set('Content-Disposition', 'attachment;filename="Posts.xlsx"');
        $response->headers->set('Cache-Control', 'max-age=0');
    
        // Clean up the temporary file
        unlink($tempFile);
    
        return $response;
    }
}
